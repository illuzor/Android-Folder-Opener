plugins {
    id("java")
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "com.illuzor.afo"
version = "1.1.1"

repositories {
    mavenCentral()
}

kotlin{
    jvmToolchain(21)
}

ktlint {
    version.set("1.5.0")
}

intellij {
    version.set("2021.3.3")
    type.set("IC")
    updateSinceUntilBuild.set(false)
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.3"))
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
