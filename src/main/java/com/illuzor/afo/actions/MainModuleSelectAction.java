package com.illuzor.afo.actions;

import com.illuzor.afo.constants.Id;
import com.illuzor.afo.ui.ChooserDialogWrapper;
import com.illuzor.afo.utils.ModulesUtil;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class MainModuleSelectAction extends ActionBase {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);

        List<String> modules = getModulesList(project.getBasePath());

        if (modules == null) {
            showError("Unable to open file 'settings.gradle'");
        } else if(modules.isEmpty()){
            showError("No modules found");
        } else {
            showChooser(project, modules);
        }
    }

    private List<String> getModulesList(String basePath) {
        try {
            return ModulesUtil.getModulesList(basePath);
        } catch (IOException e) {
            return null;
        }
    }

    private void showChooser(Project project, List<String> modules){
        ChooserDialogWrapper dialog = new ChooserDialogWrapper(project, modules);
        dialog.onOkClickedListener(moduleName ->
                PropertiesComponent.getInstance(project).setValue(Id.MAIN_MODULE_KEY, moduleName)
        );
        dialog.show();
    }
}
