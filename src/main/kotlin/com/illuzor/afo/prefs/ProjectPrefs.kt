package com.illuzor.afo.prefs

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.project.Project

internal class ProjectPrefs(
    project: Project,
) {
    private val projectProps = PropertiesComponent.getInstance(project)

    var appModulePath by stringPref(projectProps, defaultValue = DEFAULT_APP_MODULE_PATH)

    private companion object {
        const val DEFAULT_APP_MODULE_PATH = "app"
    }
}
