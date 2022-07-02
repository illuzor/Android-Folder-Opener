package com.illuzor.afo.actions.define

import com.illuzor.afo.actions.BaseAction
import com.illuzor.afo.ext.gradleFile
import com.illuzor.afo.ext.isGradleModule
import com.illuzor.afo.ext.notExists
import com.illuzor.afo.ui.showErrorDialog
import com.illuzor.afo.ui.showInfoNotification
import java.io.File

internal abstract class DefineAppModuleBaseAction : BaseAction() {

    fun checkModuleFolderAndSave(
        moduleFolder: File,
        moduleName: String,
    ) {
        val modulePath = moduleFolder.path

        if (!moduleFolder.isGradleModule) {
            showErrorDialog("Folder '$modulePath' is not a gradle module")
            return
        }

        val gradleFile = moduleFolder.gradleFile
        if (gradleFile == null || gradleFile.notExists()) {
            showErrorDialog("Folder '$modulePath' does not contain a gradle file")
            return
        }

        saveModule(moduleName)
    }

    fun saveModule(moduleName: String) {
        projectPrefs.appModulePath = moduleName
        showInfoNotification("Application module is set to '$moduleName'")
    }
}
