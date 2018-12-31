package com.illuzor.afo.actions;

import com.illuzor.afo.constants.Id;
import com.illuzor.afo.ui.ChooserDialogWrapper;
import com.illuzor.afo.utils.ModulesUtil;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class MainModuleSelectAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        try {
            List<String> modulesList = ModulesUtil.getModulesList(project.getBasePath());
            if (modulesList.isEmpty()) {
                Messages.showErrorDialog("No modules found", "Error");
            } else {
                ChooserDialogWrapper dialog = new ChooserDialogWrapper(project, modulesList);
                dialog.onOkClickedListener(moduleName -> PropertiesComponent.getInstance(project).setValue(Id.MAIN_MODULE_KEY, moduleName));
                dialog.show();
            }
        } catch (IOException e1) {
            Messages.showErrorDialog("Unable to open file 'settings.gradle'", "Error");
        }
    }
}
