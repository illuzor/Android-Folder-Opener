package com.illuzor.afo.actions.define

import com.illuzor.afo.constants.Prefs.MAIN_MODULE_KEY
import com.illuzor.afo.ui.showErrorDialog
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import java.io.File

class MainModuleEnterAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project: Project = e.getData<Project>(PlatformDataKeys.PROJECT) ?: return
        val moduleName = showInput(project)
        if (moduleName == null || moduleName.isEmpty()) {
            return
        }
        val path = project.basePath + "/" + moduleName + "/build.gradle"
        val ktsPath = "$path.kts"
        if (!File(path).exists() && !File(ktsPath).exists()) {
            showErrorDialog("Module '$moduleName' Does Not Exists")
            return
        }
        PropertiesComponent.getInstance(project).setValue(MAIN_MODULE_KEY, moduleName)
    }

    private fun showInput(project: Project): String? {
        return Messages.showInputDialog(
            project,
            "Enter Main Module Name",
            "Main Module Name:",
            Messages.getQuestionIcon()
        )
    }
}
