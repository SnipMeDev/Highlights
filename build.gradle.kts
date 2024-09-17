import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

apply(from = "publish-root.gradle")

plugins {
    kotlin("multiplatform") version "2.0.20"
    kotlin("plugin.serialization") version "2.0.20"
    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    id("signing")
}

group = "dev.snipme"
version = "1.0.0"

kotlin {
    // Android
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    // iOS

    val xcf = XCFramework()
    val iosTargets = listOf(iosX64(), iosArm64(), iosSimulatorArm64())

    iosTargets.forEach {
        it.binaries.framework {
            baseName = "highlights"
            xcf.add(this)
        }
    }
    // Desktop
    mingwX64()
    linuxX64()
    linuxArm64()
    macosX64()
    macosArm64()
    // Web
    js {
        browser()
        nodejs()
    }
    wasmJs()
    // Dependencies
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
            }
        }

        val commonTest by getting {
            dependencies {
                // Coroutines test
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
                implementation(kotlin("test"))
            }
        }
    }
}

publishing {
    val emptyJar = tasks.register<Jar>("emptyJar") {
        archiveAppendix.set("empty")
    }

    publications.forEach {
        val publication = it as? MavenPublication ?: return@forEach

        publication.artifact(emptyJar) {
            classifier = "javadoc"
        }

        publication.pom.withXml {
            val root = asNode()
            root.appendNode("name", project.name)
            root.appendNode(
                "description",
                "Kotlin Multiplatform syntax highlighting engine."
            )
            root.appendNode("url", "https://github.com/SnipMeDev/Highlights")

            root.appendNode("licenses").apply {
                appendNode("license").apply {
                    appendNode("name", "The Apache Software License, Version 2.0")
                    appendNode("url", "https://www.apache.org/licenses/LICENSE-2.0.txt")
                    appendNode("distribution", "repo")
                }
            }

            root.appendNode("developers").apply {
                appendNode("developer").apply {
                    appendNode("id", "tkadziolka")
                    appendNode("name", "Tomasz Kądziołka")
                    appendNode("email", "kontakt@tkadziolka.pl")
                }
            }

            root.appendNode("scm").apply {
                appendNode(
                    "connection",
                    "scm:git:ssh://git@github.com:SnipMeDev/Highlights.git"
                )
                appendNode(
                    "developerConnection",
                    "scm:git:ssh://git@github.org:SnipMeDev/Highlights.git",
                )
                appendNode("url", "https://github.com/SnipMeDev/Highlights")
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        rootProject.ext["signing.keyId"] as String,
        rootProject.ext["signing.key"] as String,
        rootProject.ext["signing.password"] as String
    )
    sign(publishing.publications)
}