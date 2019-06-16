package com.illuzor.afo.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.ui.Messages;

abstract class ActionBase extends AnAction {
    void showError(String message) {
        Messages.showErrorDialog(message, "Error");
    }
}
