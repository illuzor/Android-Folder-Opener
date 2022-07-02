package com.illuzor.afo.actions.define

import com.illuzor.afo.ext.notExists
import com.illuzor.afo.ui.showErrorDialog
import com.intellij.openapi.ui.Messages
import java.io.File

internal class MainModuleEnterAction : DefineMainModuleBaseAction() {

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

        checkModuleFolderAndSave(moduleFolder, moduleName)
    }

    private fun showInput(): String? =
        Messages.showInputDialog(
            project,
            "Enter main module name. For example 'module' or 'module:submodule'",
            "Main Module Name:",
            Messages.getQuestionIcon(),
        )
}
