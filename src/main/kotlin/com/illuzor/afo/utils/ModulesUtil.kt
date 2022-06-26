package com.illuzor.afo.utils

import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.util.regex.Pattern

object ModulesUtil {

    @Throws(IOException::class)
    fun readFileToString(file: File): String {
        val builder = StringBuilder()
        Files.lines(file.toPath(), StandardCharsets.UTF_8).forEach { str: String? -> builder.append(str) }
        return builder.toString()
    }

    fun getModulesFromString(string: String?): List<String> {
        val modules: MutableList<String> = ArrayList()
        val matcher = Pattern.compile("['\"]:(.+?)['\"]").matcher(string)
        while (matcher.find()) {
            modules.add(matcher.group(1).replace(":".toRegex(), "/"))
        }
        return modules
    }

    @Throws(IOException::class)
    fun getModulesList(basePath: String): List<String> {
        val path = "$basePath/settings.gradle"
        var file = File(path)
        if (!file.exists()) {
            file = File("$path.kts")
        }
        return getModulesFromString(readFileToString(file))
    }
}