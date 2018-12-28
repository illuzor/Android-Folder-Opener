package com.illuzor.afo.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.IOException;

abstract class OpenFolderAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final Project project = e.getData(PlatformDataKeys.PROJECT);
        String projectPath = project.getBasePath();
        String folderPath = projectPath + "/" + getModulePath() + "/build/" + getFolderPath();
        File folder = new File(folderPath);

        if (!folder.exists()) {
            System.out.println("Folder does not exists");
            return;
        }
        try {
            Desktop.getDesktop().open(new File(folderPath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @NotNull
    private String getModulePath() {
        return "app";
    }

    @NotNull
    abstract String getFolderPath();
}
