import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

apply(from = "publish-root.gradle")

plugins {
    kotlin("multiplatform") version "1.8.22"
    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    id("signing")
}

group = "dev.snipme"
version = "0.4.1-SNAPSHOT"

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
    publications {
        create<MavenPublication>("release") {
            from(components["kotlin"])

            groupId = group as String
            artifactId = "highlights"
            version = version as String

            pom {
                name.set("Highlights")
                description.set("Kotlin Multiplatform (KMM) syntax highlighting engine")
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
