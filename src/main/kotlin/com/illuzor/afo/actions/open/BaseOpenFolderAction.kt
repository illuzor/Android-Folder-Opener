package com.illuzor.afo.actions.open

import com.illuzor.afo.actions.BaseAction
import com.illuzor.afo.ext.notExists
import com.illuzor.afo.ui.showErrorDialog
import java.awt.Desktop
import java.io.File
import java.io.IOException

internal abstract class BaseOpenFolderAction : BaseAction() {

    abstract val folderPath: String

    override fun perform() {
        val modulePath = projectPrefs.appModulePath
        val moduleFolder = File("$projectPath/$modulePath")
        if (moduleFolder.notExists()) {
            showErrorDialog("Module '$modulePath' does not exist")
            return
        }

        val folderToOpenPath = "$projectPath/$modulePath/build/$folderPath"
        val folderToOpen = File(folderToOpenPath)
        if (folderToOpen.notExists()) {
            showErrorDialog("Folder '$folderToOpenPath' does not exist")
            return
        }
        openFolder(folderToOpen)
    }

    private fun openFolder(folder: File) =
        try {
            Desktop.getDesktop().open(folder)
        } catch (e: IOException) {
            e.printStackTrace()
            showErrorDialog("Unable to open folder '${folder.path}'${e.message}")
        }
}
