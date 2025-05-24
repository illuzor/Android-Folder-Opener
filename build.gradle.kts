import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    id("org.jlleitschuh.gradle.ktlint") version "12.3.0"
    id("org.jetbrains.kotlin.jvm") version "2.1.21"
    id("org.jetbrains.intellij.platform") version "2.6.0"
}

val sinceBuildIdeaVersion = "213.7172"
val verificationIdeaVersion = "2023.2.7"

group = "com.illuzor.afo"
version = "1.1.1"

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
        localPlatformArtifacts()
    }
}

kotlin {
    jvmToolchain(21)
}

ktlint {
    version.set("1.6.0")
}

intellijPlatform {
    pluginConfiguration {
        version = project.version.toString()
        name = "Android Folder Opener"

        ideaVersion {
            sinceBuild = sinceBuildIdeaVersion
        }
    }

    pluginVerification {
        ides {
            ide(IntelliJPlatformType.IntellijIdeaCommunity, version = verificationIdeaVersion)
        }
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
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

    intellijPlatform {
        intellijIdeaCommunity(version = verificationIdeaVersion)
        pluginVerifier()
        testFramework(TestFrameworkType.Platform)
    }
}
