package com.illuzor.afo.actions;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.IOException;

abstract class OpenFolderAction extends AnAction {

    private static final String GROUP_DISPLAY_ID = "Folder Opener";

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final Project project = e.getData(PlatformDataKeys.PROJECT);
        final String projectPath = project.getBasePath();
        final String modulePath = getModulePath();

        if (!new File(projectPath + "/" + getModulePath()).exists()) {
            showNotification("Module does not exists", modulePath, NotificationType.ERROR);
        }

        final String folderPath = projectPath + "/" + modulePath + "/build/" + getFolderPath();
        final File folder = new File(folderPath);

        if (!folder.exists()) {
            showNotification("Folder does not exists", folderPath, NotificationType.WARNING);
            return;
        }
        try {
            Desktop.getDesktop().open(new File(folderPath));
        } catch (IOException ex) {
            ex.printStackTrace();
            showNotification("Unable to open folder", folderPath + "\n" + ex.getMessage(), NotificationType.ERROR);
        }
    }

    private void showNotification(String title, String message, NotificationType type) {
        Notifications.Bus.notify(new Notification(GROUP_DISPLAY_ID, title, message, type));
    }

    @NotNull
    private String getModulePath() {
        return "app";
    }

    @NotNull
    abstract String getFolderPath();
}
