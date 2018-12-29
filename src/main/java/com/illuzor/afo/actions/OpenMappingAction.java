package com.illuzor.afo.actions;

import org.jetbrains.annotations.NotNull;

public class OpenMappingAction extends OpenFolderAction {
    @NotNull
    @Override
    String getFolderPath() {
        return "outputs/mapping";
    }
}
