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
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("org.jetbrains.kotlin.jvm") version "1.7.21"
    id("org.jetbrains.intellij") version "1.10.0"
}

group = "com.illuzor.afo"
version = "1.1.1"

repositories {
    mavenCentral()
}

ktlint {
    version.set("0.45.2")
    disabledRules.set(
        setOf(
            "no-wildcard-imports",
            "import-ordering",
        )
    )
}

intellij {
    version.set("2021.2")
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
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
