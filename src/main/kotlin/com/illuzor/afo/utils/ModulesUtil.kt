package com.illuzor.afo.utils

import com.illuzor.afo.ext.notExists
import java.io.File
import java.io.IOException
import java.util.regex.Pattern

internal object ModulesUtil {

    @OptIn(ExperimentalStdlibApi::class)
    fun getModulesFromString(string: String): List<String> {
        val matcher = Pattern.compile("['\"]:(.+?)['\"]").matcher(string)

        return buildList {
            while (matcher.find()) {
                add(matcher.group(1).replace(":".toRegex(), "/"))
            }
        }
    }

    @Throws(IOException::class)
    fun getModulesList(basePath: String): List<String> {
        val path = "$basePath/settings.gradle"
        var file = File(path)
        if (file.notExists()) {
            file = File("$path.kts")
        }
        return try {
            getModulesFromString(file.readText())
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}
