buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/") // for ktlint-gradle plugin
    }
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle")
    }
}

plugins {
    id("java")
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("org.jetbrains.intellij") version "1.17.3"
}

group = "com.illuzor.afo"
version = "1.1.1"

repositories {
    mavenCentral()
}

ktlint {
    version.set("1.3.0")
}

intellij {
    version.set("2021.3.3")
    type.set("IC")
    updateSinceUntilBuild.set(false)
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
