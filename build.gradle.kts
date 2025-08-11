import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    id("org.jlleitschuh.gradle.ktlint") version "13.0.0"
    id("org.jetbrains.kotlin.jvm") version "2.2.0"
    id("org.jetbrains.intellij.platform") version "2.7.1"
}

val sinceBuildIdeaVersion = "213.7172"
val verificationIdeaVersion = "2023.2.7"

group = "com.illuzor.afo"
version = "1.2.1"

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
    version.set("1.7.1")
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
            create(
                type = IntelliJPlatformType.IntellijIdeaCommunity,
                version = verificationIdeaVersion,
            )
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
    testImplementation(platform("org.junit:junit-bom:5.13.4"))
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("junit:junit:4.13.2") // tests fails without this dependency

    intellijPlatform {
        intellijIdeaCommunity(version = verificationIdeaVersion)
        pluginVerifier()
        testFramework(TestFrameworkType.JUnit5)
    }
}
