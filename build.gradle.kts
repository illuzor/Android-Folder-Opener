import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    alias(libs.plugins.ktlint.gradle.plugin)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.intellij.platform)
}

val sinceBuildIdeaVersion = libs.versions.sinceBuildIdea.get()
val verificationIdeaVersion = libs.versions.verificationIdea.get()

group = "com.illuzor.afo"
version = "1.2.2"

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
        localPlatformArtifacts()
    }
}

kotlin {
    jvmToolchain(libs.versions.jvmTarget.get().toInt())
}

val ktlintVersion = libs.versions.ktlint.core.get()
ktlint {
    version.set(ktlintVersion)
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
        sourceCompatibility = JvmTarget.JVM_11.target
        targetCompatibility = JvmTarget.JVM_11.target
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    test {
        useJUnitPlatform()
    }
}

dependencies {
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter.params)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(libs.junit.legacy) // tests fails without this dependency

    intellijPlatform {
        intellijIdeaCommunity(version = verificationIdeaVersion)
        pluginVerifier()
        testFramework(TestFrameworkType.JUnit5)
    }
}
