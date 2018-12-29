package com.illuzor.afo.actions;

import org.jetbrains.annotations.NotNull;

public class OpenBundleDebugAction extends OpenFolderAction {
    @NotNull
    @Override
    String getFolderPath() {
        return "outputs/bundle/debug";
    }
}
