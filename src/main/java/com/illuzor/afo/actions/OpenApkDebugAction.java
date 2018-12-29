package com.illuzor.afo.actions;

import org.jetbrains.annotations.NotNull;

public class OpenApkDebugAction extends OpenFolderAction {
    @NotNull
    @Override
    String getFolderPath() {
        return "outputs/apk/debug";
    }
}
