package com.illuzor.afo.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jdesktop.swingx.JXRadioGroup;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;
import java.util.function.Consumer;

public class ChooserDialogWrapper extends DialogWrapper {

    private final List<String> modulesList;
    private JXRadioGroup<String> group;
    private Consumer<String> okClickListener;

    public void onOkClickedListener(Consumer<String> okClickListener) {
        this.okClickListener = okClickListener;
    }

    public ChooserDialogWrapper(@Nullable Project project, List<String> modulesList) {
        super(project);
        this.modulesList = modulesList;
        setTitle("Select Main Module");
        setOKButtonText("Select");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        String[] modulesArray = new String[modulesList.size()];
        for (int i = 0; i < modulesList.size(); i++) {
            modulesArray[i] = modulesList.get(i);
        }

        group = new JXRadioGroup<>(modulesArray);
        group.setSelectedValue(modulesArray[0]);
        group.setLayoutAxis(BoxLayout.Y_AXIS);
        return group;
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();
        okClickListener.accept(group.getSelectedValue());
    }
}
