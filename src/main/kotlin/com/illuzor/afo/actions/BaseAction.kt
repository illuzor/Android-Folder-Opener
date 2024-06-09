package com.illuzor.afo.actions

import com.illuzor.afo.prefs.ProjectPrefs
import com.illuzor.afo.ui.showErrorNotification
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project

internal abstract class BaseAction : AnAction() {
    protected lateinit var project: Project
    protected lateinit var projectPath: String
    protected lateinit var projectPrefs: ProjectPrefs

    final override fun actionPerformed(e: AnActionEvent) {
        val project = e.getData(PlatformDataKeys.PROJECT)
        if (project == null) {
            showErrorNotification("Unable to initialize project")
            return
        }
        this.project = project

        val projectPath = project.basePath
        if (projectPath == null) {
            showErrorNotification("Unable to initialize projectPath")
            return
        }

        this.projectPath = projectPath
        this.projectPrefs = ProjectPrefs(project)

        perform()
    }

    abstract fun perform()
}
