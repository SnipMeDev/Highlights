import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.publish.maven.MavenPublication

plugins {
    id("java-library")
    kotlin("jvm") version "1.8.21"
    id("maven-publish")
}

val libraryName = "Highlights"
val libraryDescription = "Kotlin Multiplatform (KMM) syntax highlighting engine"

group = "dev.snipme"
version = "0.1.0-SNAPSHOT"

publishing {
    publications {
        create<MavenPublication>("HighlightsPublication") {
            from(components["java"])
            groupId = group as String
            artifactId = libraryName
            version = version as String

            pom {
                name.set(libraryName)
                description.set(libraryDescription)
                url.set("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            }
        }
    }

    repositories {
        mavenLocal()
    }
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