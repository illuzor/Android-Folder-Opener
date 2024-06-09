package com.illuzor.afo.actions.define

import com.illuzor.afo.ui.showErrorDialog
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.vfs.VirtualFile
import java.io.File

internal class AppModuleBrowseAction : DefineAppModuleBaseAction() {
    override fun perform() {
        val selectedFolder: VirtualFile =
            FileChooser.chooseFile(
                FileChooserDescriptorFactory.createSingleFolderDescriptor(),
                project,
                project.projectFile,
            ) ?: return

        val selectedFolderPath = selectedFolder.path
        if (selectedFolderPath == projectPath) {
            showErrorDialog("Root folder of the '${project.name}' project selected. Please select a module folder")
            return
        }

        if (!selectedFolderPath.contains(projectPath)) {
            showErrorDialog("Selected folder is not in the '${project.name}' project folder")
            return
        }

        val moduleFolder = File(selectedFolder.path)
        val moduleName = selectedFolderPath.removePrefix("$projectPath/")
        checkModuleFolderAndSave(moduleFolder, moduleName)
    }
}
