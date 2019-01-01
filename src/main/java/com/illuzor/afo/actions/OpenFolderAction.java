package com.illuzor.afo.actions;

import com.illuzor.afo.constants.Id;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.IOException;

abstract class OpenFolderAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        String projectPath = project.getBasePath();
        String modulePath = getModulePath(PropertiesComponent.getInstance(project));

        if (!new File(projectPath + "/" + modulePath).exists()) {
            showError("Module '" + modulePath + "' does not exists");
            return;
        }

        String folderPath = projectPath + "/" + modulePath + "/build/" + getFolderPath();
        File folder = new File(folderPath);

        if (!folder.exists()) {
            showError("Folder '" + folderPath + "' does not exists");
            return;
        }
        try {
            Desktop.getDesktop().open(new File(folderPath));
        } catch (IOException ex) {
            ex.printStackTrace();
            showError("Unable to open folder '" + folderPath + "'\n" + ex.getMessage());
        }
    }

    private void showError(String message) {
        Messages.showErrorDialog(message, "Error");
    }

    @NotNull
    private String getModulePath(PropertiesComponent pc) {
        return pc.getValue(Id.MAIN_MODULE_KEY, "app");
    }

    @NotNull
    abstract String getFolderPath();
}
