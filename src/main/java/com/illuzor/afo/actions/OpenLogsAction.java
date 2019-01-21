package com.illuzor.afo.actions;

import org.jetbrains.annotations.NotNull;

public class OpenLogsAction extends OpenFolderAction {
    @NotNull
    @Override
    String getFolderPath() {
        return "outputs/logs";
    }
}
