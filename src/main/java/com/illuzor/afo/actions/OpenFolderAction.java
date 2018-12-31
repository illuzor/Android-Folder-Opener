package com.illuzor.afo.actions;

import com.illuzor.afo.constants.Id;
import com.intellij.ide.util.PropertiesComponent;
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
        Project project = e.getData(PlatformDataKeys.PROJECT);
        String projectPath = project.getBasePath();
        String modulePath = getModulePath(PropertiesComponent.getInstance(project));

        if (!new File(projectPath + "/" + modulePath).exists()) {
            showNotification("Module does not exists", modulePath);
            return;
        }

        String folderPath = projectPath + "/" + modulePath + "/build/" + getFolderPath();
        File folder = new File(folderPath);

        if (!folder.exists()) {
            showNotification("Folder does not exists", folderPath);
            return;
        }
        try {
            Desktop.getDesktop().open(new File(folderPath));
        } catch (IOException ex) {
            ex.printStackTrace();
            showNotification("Unable to open folder", folderPath + "\n" + ex.getMessage());
        }
    }

    private void showNotification(String title, String message) {
        Notifications.Bus.notify(new Notification(GROUP_DISPLAY_ID, title, message, NotificationType.INFORMATION));
    }

    @NotNull
    private String getModulePath(PropertiesComponent pc) {
        return pc.getValue(Id.MAIN_MODULE_KEY, "app");
    }

    @NotNull
    abstract String getFolderPath();
}
