import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.publish.maven.MavenPublication
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

apply(from = "publish-root.gradle")

plugins {
    kotlin("multiplatform") version "1.8.22"
    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    id("signing")
}

val libraryName = "Highlights"
val libraryDescription = "Kotlin Multiplatform (KMM) syntax highlighting engine"

group = "dev.snipme"
version = "0.2.0-SNAPSHOT"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }


    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val nativeMain by getting
        val nativeTest by getting
    }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["kotlin"]) // jar

            groupId = group as String
            artifactId = libraryName.toLowerCase()
            version = version as String

            pom {
                name.set(libraryName)
                description.set(libraryDescription)
                url.set("https://github.com/SnipMeDev/Highlights")

                licenses {
                    license {
                        name.set("Apache-2.0")
                        url.set("https://opensource.org/license/apache-2-0")
                    }
                }

                developers {
                    developer {
                        id.set("tkadziolka")
                        name.set("Tomasz Kądziołka")
                        email.set("kontakt@tkadziolka.pl")
                    }
                }
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

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

tasks.withType<Wrapper> {
    gradleVersion = "7.6"
    distributionType = Wrapper.DistributionType.BIN
}