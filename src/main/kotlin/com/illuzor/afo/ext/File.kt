package com.illuzor.afo.ext

import java.io.File

private const val GRADLE_FILE_NAME = "build.gradle"
private const val GRADLE_KTS_FILE_NAME = "build.gradle.kts"

private const val SETTINGS_GRADLE_FILE_NAME = "settings.gradle"
private const val SETTINGS_GRADLE_KTS_FILE_NAME = "settings.gradle.kts"

internal val File.isGradleModule: Boolean
    get() {
        if (isNotDirectoryOrEmpty) {
            return false
        }

        return resolve(GRADLE_FILE_NAME).isFileAndExists || resolve(GRADLE_KTS_FILE_NAME).isFileAndExists
    }

internal val File.gradleFile: File?
    get() = getFile(GRADLE_FILE_NAME, GRADLE_KTS_FILE_NAME)

internal val File.settingsGradleFile: File?
    get() = getFile(SETTINGS_GRADLE_FILE_NAME, SETTINGS_GRADLE_KTS_FILE_NAME)

private fun File.getFile(vararg filenames: String): File? {
    if (isNotDirectoryOrEmpty) {
        return null
    }

    return filenames.map(::resolve).firstOrNull(File::isFileAndExists)
}

private val File.isNotDirectoryOrEmpty: Boolean
    get() = !this.isDirectory || this.listFiles().isNullOrEmpty()

private val File.isFileAndExists: Boolean
    get() = this.exists() && this.isFile

internal fun File.notExists(): Boolean = !this.exists()
