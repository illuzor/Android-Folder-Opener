package com.illuzor.afo.actions;

import org.jetbrains.annotations.NotNull;

public class OpenApkAction extends OpenFolderAction {
    @NotNull
    @Override
    String getFolderPath() {
        return "outputs/apk";
    }
}
