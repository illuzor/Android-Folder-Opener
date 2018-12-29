package com.illuzor.afo.actions;

import org.jetbrains.annotations.NotNull;

public class OpenBundleReleaseAction extends OpenFolderAction {
    @NotNull
    @Override
    String getFolderPath() {
        return "outputs/bundle/release";
    }
}
