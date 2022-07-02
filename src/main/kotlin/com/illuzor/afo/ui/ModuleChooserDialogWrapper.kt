package com.illuzor.afo.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import org.jdesktop.swingx.JXRadioGroup
import javax.swing.BoxLayout
import javax.swing.JComponent

internal class ModuleChooserDialogWrapper(
    project: Project?,
    private val modulesList: List<String>,
) : DialogWrapper(project) {

    private var group: JXRadioGroup<String?>? = null
    private var onChooseListener: ((String) -> Unit)? = null

    init {
        title = "Select Main Module"
        setOKButtonText("Select")
        init()
    }

    fun onChoose(listener: (String) -> Unit) {
        onChooseListener = listener
    }

    override fun createCenterPanel(): JComponent? {
        group = JXRadioGroup(modulesList.toTypedArray()).apply {
            selectedValue = modulesList.firstOrNull()
            setLayoutAxis(BoxLayout.Y_AXIS)
        }
        return group
    }

    override fun doOKAction() {
        super.doOKAction()
        val selectedValue = group?.selectedValue ?: return
        onChooseListener?.invoke(selectedValue)
    }
}
