package com.illuzor.afo.ext

import java.util.regex.Pattern

private const val MODULE_REGEXP = "['\"]:(.+?)['\"]"

@OptIn(ExperimentalStdlibApi::class)
internal fun String.extractModules(): List<String> {
    val matcher = Pattern.compile(MODULE_REGEXP).matcher(this)

    return buildList {
        while (matcher.find()) {
            add(matcher.group(1).replace(":".toRegex(), "/"))
        }
    }
}
