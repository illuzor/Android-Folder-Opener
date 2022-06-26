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
    type.set("IC") // Target IDE Platform

    //plugins.set(listOf(/* Plugin Dependencies */))
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

    /*patchPluginXml {
        sinceBuild.set("211")
        untilBuild.set("222.*")
    }*/

    /*signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }*/

    /*publishPlugin {
         token.set(System.getenv("PUBLISH_TOKEN"))
     }*/
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
}