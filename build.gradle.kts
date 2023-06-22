import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.publish.maven.MavenPublication

plugins {
    id("java-library")
    kotlin("jvm") version "1.8.21"
    id("maven-publish")
}

group = "dev.snipme"
version = "0.1.0"

publishing {
    publications {
        create<MavenPublication>("HighlightsPublication") {
            from(components["java"])
            groupId = group as String
            artifactId = "Highlights"
            version = version as String
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