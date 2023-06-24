import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.publish.maven.MavenPublication
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

apply(from = "publish-root.gradle")

plugins {
    id("java-library")
    kotlin("jvm") version "1.8.21"
    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    id("signing")
}

val libraryName = "Highlights"
val libraryDescription = "Kotlin Multiplatform (KMM) syntax highlighting engine"

group = "dev.snipme"
version = "0.1.0-SNAPSHOT"

publishing {
    publications {
        create<MavenPublication>("HighlightsPublication") {

            from(components["java"]) // jar

            groupId = group as String
            artifactId = libraryName
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

    repositories {
        mavenLocal()
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

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
}

repositories {
    mavenCentral()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}