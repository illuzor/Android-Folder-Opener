package com.illuzor.afo.actions.define

import com.illuzor.afo.ext.extractModules
import com.illuzor.afo.ext.notExists
import com.illuzor.afo.ext.settingsGradleFile
import com.illuzor.afo.ui.ModuleChooserDialogWrapper
import com.illuzor.afo.ui.showErrorDialog
import java.io.File

internal class MainModuleSelectAction : DefineMainModuleBaseAction() {

    override fun perform() {
        val projectFolder = File(projectPath)
        if (projectFolder.notExists() || projectFolder.listFiles().isNullOrEmpty()) {
            showErrorDialog("Directory '$projectPath' is not gradle project")
            return
        }

        val settingsGradleFile = projectFolder.settingsGradleFile
        if (settingsGradleFile == null || settingsGradleFile.notExists()) {
            showErrorDialog("Directory '$projectPath' does not contain settings.gradle or settings.gradle.kts file")
            return
        }

        val settingsGradleString = settingsGradleFile.readText()
        val modules = settingsGradleString.extractModules()

        if (modules.isEmpty()) {
            showErrorDialog("Unable to detect modules automatically")
        } else {
            showModuleChooser(modules)
        }
    }

    private fun showModuleChooser(modules: List<String>) =
        ModuleChooserDialogWrapper(project, modules).apply {
            onChoose(::saveModule)
            show()
        }
}
