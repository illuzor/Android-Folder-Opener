package com.illuzor.afo.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import org.jdesktop.swingx.JXRadioGroup
import java.util.function.Consumer
import javax.swing.BoxLayout
import javax.swing.JComponent

internal class ChooserDialogWrapper(project: Project?, private val modulesList: List<String>) : DialogWrapper(project) {

    private var group: JXRadioGroup<String?>? = null
    private var okClickListener: Consumer<String?>? = null

    fun onOkClickedListener(okClickListener: Consumer<String?>?) {
        this.okClickListener = okClickListener
    }

    init {
        title = "Select Main Module"
        setOKButtonText("Select")
        init()
    }

    override fun createCenterPanel(): JComponent? {
        val modulesArray = arrayOfNulls<String>(modulesList.size)
        for (i in modulesList.indices) {
            modulesArray[i] = modulesList[i]
        }
        group = JXRadioGroup(modulesArray)
        group!!.selectedValue = modulesArray[0]
        group!!.setLayoutAxis(BoxLayout.Y_AXIS)
        return group
    }

    override fun doOKAction() {
        super.doOKAction()
        okClickListener!!.accept(group!!.selectedValue)
    }
}