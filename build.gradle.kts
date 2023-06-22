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

/**
 *  Sure, here is a sample Maven publication configuration in Gradle for a library with package `dev.snipme`:

```
plugins {
id 'java'
id 'maven-publish'
}

group = 'dev.snipme'
version = '1.0.0'

repositories {
mavenCentral()
}

dependencies {
// Add your library dependencies here
}

publishing {
publications {
maven(MavenPublication) {
groupId = 'dev.snipme'
artifactId = 'your-library-name'
version = '1.0.0'

from components.java

// Add any additional publication options here, such as including sources or javadocs
}
}

repositories {
maven {
url = uri('https://your-maven-repo-url')

// Set the credentials for your Maven repository here if necessary
credentials {
username = System.getenv('MAVEN_USERNAME')
password = System.getenv('MAVEN_PASSWORD')
}
}
}
}
```

Replace `your-library-name` with the name of your library and `https://your-maven-repo-url` with the URL of your Maven repository. Also make sure to add your library dependencies to the `dependencies` block.    }
}

create<MavenPublication>("myPublication") {
from(myLibrary.components["java"])
groupId = "com.example"
artifactId = "my-library"
version = "1.0.0"
}
}
repositories {
maven {
url = uri("https://my-repo.com")
credentials {
username = System.getenv("MY_REPO_USERNAME")
password = System.getenv("MY_REPO_PASSWORD")
}
}
}
 */