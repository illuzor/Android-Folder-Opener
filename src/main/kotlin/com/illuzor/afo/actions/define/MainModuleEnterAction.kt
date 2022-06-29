package com.illuzor.afo.actions.define

import com.illuzor.afo.actions.BaseAction
import com.illuzor.afo.ext.gradleFile
import com.illuzor.afo.ext.isGradleModule
import com.illuzor.afo.ext.notExists
import com.illuzor.afo.ui.showErrorDialog
import com.intellij.openapi.ui.Messages
import java.io.File

internal class MainModuleEnterAction : BaseAction() {

    override fun perform() {
        val moduleName = showInput()?.replace(':', '/')
        if (moduleName.isNullOrEmpty()) {
            return
        }

        val modulePath = "$projectPath/$moduleName"
        val moduleFolder = File(modulePath)
        if (moduleFolder.notExists()) {
            showErrorDialog("Directory '$modulePath' does not exist")
            return
        }

        if (!moduleFolder.isGradleModule) {
            showErrorDialog("Directory '$modulePath' is not gradle module")
            return
        }

        val gradleFile = moduleFolder.gradleFile
        if (gradleFile == null || gradleFile.notExists()) {
            showErrorDialog("Directory '$modulePath' does not contain gradle file")
            return
        }

        projectPrefs.appModulePath = moduleName
    }

    private fun showInput(): String? =
        Messages.showInputDialog(
            project,
            "Enter main module name. For example 'module' or 'module:submodule'",
            "Main Module Name:",
            Messages.getQuestionIcon(),
        )
}
