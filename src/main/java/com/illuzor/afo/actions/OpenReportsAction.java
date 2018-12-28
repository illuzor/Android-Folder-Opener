package com.illuzor.afo.actions;

import org.jetbrains.annotations.NotNull;

public class OpenReportsAction extends OpenFolderAction {

    @NotNull
    @Override
    String getFolderPath() {
        return "reports";
    }
}
