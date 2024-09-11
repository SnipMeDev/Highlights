apply(from = "publish-root.gradle")

plugins {
    kotlin("multiplatform") version "1.9.23"
    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    id("signing")
}

group = "dev.snipme"
version = "0.9.2"

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
    iosX64()
    iosArm64()
    iosSimulatorArm64()
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
        val commonTest by getting {
            dependencies {
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