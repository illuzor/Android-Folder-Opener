plugins {
    id("java")
    id("org.jlleitschuh.gradle.ktlint") version "12.3.0"
    id("org.jetbrains.kotlin.jvm") version "2.1.21"
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "com.illuzor.afo"
version = "1.1.1"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

ktlint {
    version.set("1.6.0")
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
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }

    withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.12.2"))
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
