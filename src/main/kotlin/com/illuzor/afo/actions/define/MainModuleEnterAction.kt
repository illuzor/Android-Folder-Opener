package com.illuzor.afo.actions.define

import com.illuzor.afo.actions.BaseAction
import com.illuzor.afo.ext.notExists
import com.illuzor.afo.ui.showErrorDialog
import com.intellij.openapi.ui.Messages
import java.io.File

internal class MainModuleEnterAction : BaseAction() {

    override fun perform() {
        val moduleName = showInput()
        if (moduleName.isNullOrEmpty()) {
            return
        }

        val gradleConfigPath = project.basePath + "/" + moduleName + "/build.gradle"
        val gradleConfigKtsPath = "$gradleConfigPath.kts"
        if (File(gradleConfigPath).notExists() && File(gradleConfigKtsPath).notExists()) {
            showErrorDialog("Module '$moduleName' Does Not Exists")
            return
        }
        projectPrefs.appModulePath = moduleName
    }

    private fun showInput(): String? =
        Messages.showInputDialog(
            project,
            "Enter main module name",
            "Main Module Name:",
            Messages.getQuestionIcon(),
        )
}
