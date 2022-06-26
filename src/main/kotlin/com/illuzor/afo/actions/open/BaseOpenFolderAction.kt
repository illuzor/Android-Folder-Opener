package com.illuzor.afo.actions.open

import com.illuzor.afo.constants.Prefs
import com.illuzor.afo.ext.notExists
import com.illuzor.afo.ui.showError
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import java.awt.Desktop
import java.io.File
import java.io.IOException

abstract class BaseOpenFolderAction : AnAction() {

    abstract val folderPath: String

    final override fun actionPerformed(e: AnActionEvent) {
        val project: Project = e.getData(PlatformDataKeys.PROJECT) ?: return
        val projectPath = project.basePath
        val modulePath = getModulePath(PropertiesComponent.getInstance(project))
        if (File("$projectPath/$modulePath").notExists()) {
            showError("Module '$modulePath' does not exists")
            return
        }
        val folderPath = "$projectPath/$modulePath/build/$folderPath"
        val folder = File(folderPath)
        if (folder.notExists()) {
            showError("Folder '$folderPath' does not exists")
            return
        }
        openFolder(folder)
    }

    private fun openFolder(folder: File) {
        try {
            Desktop.getDesktop().open(folder)
        } catch (e: IOException) {
            e.printStackTrace()
            showError("""Unable to open folder '${folder.path}'${e.message}""")
        }
    }

    private fun getModulePath(pc: PropertiesComponent): String {
        return pc.getValue(Prefs.MAIN_MODULE_KEY, "app")
    }
}
