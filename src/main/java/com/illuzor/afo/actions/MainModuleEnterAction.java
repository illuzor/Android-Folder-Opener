package com.illuzor.afo.actions;

import com.illuzor.afo.constants.Id;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class MainModuleEnterAction extends ActionBase {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        String moduleName = showInput(project);

        if (moduleName == null || moduleName.isEmpty()) {
            return;
        }

        String path = project.getBasePath() + "/" + moduleName + "/build.gradle";
        String ktsPath = path + ".kts";
        if (!new File(path).exists() && !new File(ktsPath).exists()) {
            showError("Module '" + moduleName + "' Does Not Exists");
            return;
        }
        PropertiesComponent.getInstance(project).setValue(Id.MAIN_MODULE_KEY, moduleName);
    }

    private String showInput(Project project) {
        return Messages.showInputDialog(
                project,
                "Enter Main Module Name",
                "Main Module Name:",
                Messages.getQuestionIcon()
        );
    }
}
