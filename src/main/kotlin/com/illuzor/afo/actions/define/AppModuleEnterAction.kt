package com.illuzor.afo.actions.define

import com.illuzor.afo.ext.notExists
import com.illuzor.afo.ui.showErrorDialog
import com.intellij.openapi.ui.Messages
import java.io.File

internal class AppModuleEnterAction : DefineAppModuleBaseAction() {

    override fun perform() {
        val moduleName = showInput()?.replace(':', '/')
        if (moduleName.isNullOrEmpty()) {
            return
        }

        val modulePath = "$projectPath/$moduleName"
        val moduleFolder = File(modulePath)
        if (moduleFolder.notExists()) {
            showErrorDialog("Folder '$modulePath' does not exist")
            return
        }

        checkModuleFolderAndSave(moduleFolder, moduleName)
    }

    private fun showInput(): String? =
        Messages.showInputDialog(
            project,
            "Enter app module name. For example 'module' or 'module:submodule'",
            "App Module Name:",
            Messages.getQuestionIcon(),
        )
}
