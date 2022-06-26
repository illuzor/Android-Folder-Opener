package com.illuzor.afo.ui

import com.intellij.openapi.ui.Messages

fun showError(message: String) {
    Messages.showErrorDialog(message, "Error")
}