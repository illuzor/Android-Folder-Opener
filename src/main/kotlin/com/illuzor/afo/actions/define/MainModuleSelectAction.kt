package com.illuzor.afo.actions.define

import com.illuzor.afo.constants.Prefs.MAIN_MODULE_KEY
import com.illuzor.afo.ui.ChooserDialogWrapper
import com.illuzor.afo.ui.showErrorDialog
import com.illuzor.afo.utils.ModulesUtil
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import java.io.IOException
import java.util.function.Consumer

class MainModuleSelectAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project: Project = e.getData(PlatformDataKeys.PROJECT) ?: return
        val modules = getModulesList(project.basePath)
        if (modules == null) {
            showErrorDialog("Unable to open file 'settings.gradle'")
        } else if (modules.isEmpty()) {
            showErrorDialog("No modules found")
        } else {
            showChooser(project, modules)
        }
    }

    private fun getModulesList(basePath: String?): List<String>? {
        return try {
            ModulesUtil.getModulesList(basePath!!)
        } catch (e: IOException) {
            null
        }
    }

    private fun showChooser(project: Project, modules: List<String>) {
        val dialog = ChooserDialogWrapper(project, modules)
        dialog.onOkClickedListener(
            Consumer { moduleName: String? ->
                PropertiesComponent.getInstance(project).setValue(MAIN_MODULE_KEY, moduleName)
            }
        )
        dialog.show()
    }
}
