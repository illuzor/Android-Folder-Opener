plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.0"
    id("org.jetbrains.intellij") version "1.6.0"
}

group = "com.illuzor.afo"
version = "1.1.0"

repositories {
    mavenCentral()
}

intellij {
    version.set("2021.2")
    type.set("IC")
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
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
