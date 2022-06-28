package com.illuzor.afo.ext

import java.io.File

private const val GRADLE_FILE_NAME = "build.gradle"
private const val GRADLE_KTS_FILE_NAME = "build.gradle.kts"

internal val File.isGradleModule: Boolean
    get() {
        if (isNotDirectoryOrEmpty) {
            return false
        }

        return resolve(GRADLE_FILE_NAME).isFileAndExists || resolve(GRADLE_KTS_FILE_NAME).isFileAndExists
    }

internal val File.gradleFile: File?
    get() {
        if (isNotDirectoryOrEmpty) {
            return null
        }

        val gradleFile = resolve(GRADLE_FILE_NAME)
        val gradleKtsFile = resolve(GRADLE_KTS_FILE_NAME)

        return when {
            gradleFile.isFileAndExists -> gradleFile
            gradleKtsFile.isFileAndExists -> gradleKtsFile
            else -> null
        }
    }

private val File.isNotDirectoryOrEmpty: Boolean
    get() = !this.isDirectory || this.listFiles().isNullOrEmpty()

private val File.isFileAndExists: Boolean
    get() = this.exists() && this.isFile

internal fun File.notExists(): Boolean = !this.exists()
