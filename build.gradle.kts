import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.intellij.platform.gradle.models.ProductRelease
import org.jetbrains.intellij.platform.gradle.providers.ProductReleasesFilterParameters
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    alias(libs.plugins.ktlint.gradle.plugin)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.intellij.platform)
}

val sinceBuildIdeaVersion = libs.versions.sinceBuildIdea.get()
val platformIdeaVersion = libs.versions.platformIdea.get()
val oldestAndroidStudioVersion = libs.versions.oldestAndroidStudio.get()
val pluginJvmTarget = JvmTarget.fromTarget(libs.versions.jvmTarget.get())

// the newest released IDE of the given type, resolved on every build, so new releases are picked up automatically
fun latestReleaseOf(type: IntelliJPlatformType): ProductReleasesFilterParameters.() -> Unit =
    {
        types = listOf(type)
        channels = listOf(ProductRelease.Channel.RELEASE)
        sinceBuild = sinceBuildIdeaVersion
        untilBuild = ""
    }

group = "com.illuzor.afo"
version = "1.3.0"

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
        localPlatformArtifacts()
    }
}

kotlin {
    jvmToolchain(
        libs.versions.jvmToolchain
            .get()
            .toInt(),
    )
}

val ktlintVersion =
    libs.versions.ktlint.core
        .get()
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
            // the oldest supported IDEs
            create(
                type = IntelliJPlatformType.IntellijIdeaCommunity,
                version = platformIdeaVersion,
            )
            create(
                type = IntelliJPlatformType.AndroidStudio,
                version = oldestAndroidStudioVersion,
            )
            // and the newest ones available at build time
            latest(latestReleaseOf(IntelliJPlatformType.IntellijIdeaCommunity))
            latest(latestReleaseOf(IntelliJPlatformType.AndroidStudio))
        }
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = pluginJvmTarget.target
        targetCompatibility = pluginJvmTarget.target
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(pluginJvmTarget)
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
        intellijIdeaCommunity(version = platformIdeaVersion)
        pluginVerifier()
        testFramework(TestFrameworkType.JUnit5)
    }
}
