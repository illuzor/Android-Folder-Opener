package com.illuzor.afo.ui

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType.ERROR
import com.intellij.notification.Notifications
import com.intellij.openapi.ui.Messages

private const val NOTIFICATIONS_GROUP_ID = "afo_group"

internal fun showErrorDialog(message: String) {
    Messages.showErrorDialog(message, "Error")
}

internal fun showErrorNotification(title: String, message: String) {
    Notifications.Bus.notify(
        Notification(NOTIFICATIONS_GROUP_ID, title, message, ERROR)
    )
}
