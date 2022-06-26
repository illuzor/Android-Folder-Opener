package com.illuzor.afo.actions.define

import com.illuzor.afo.actions.BaseAction
import com.illuzor.afo.ui.ChooserDialogWrapper
import com.illuzor.afo.ui.showErrorDialog
import com.illuzor.afo.utils.ModulesUtil

internal class MainModuleSelectAction : BaseAction() {

    override fun perform() {
        val modules = ModulesUtil.getModulesList(projectPath)
        if (modules.isEmpty()) {
            showErrorDialog("Unable to detect modules automatically")
        } else {
            showChooser(modules)
        }
    }

    private fun showChooser(modules: List<String>) {
        ChooserDialogWrapper(project, modules).apply {
            onChoose { moduleName: String ->
                projectPrefs.appModulePath = moduleName
            }
            show()
        }
    }
}
