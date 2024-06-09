package com.illuzor.afo.ui

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.NotificationType.ERROR
import com.intellij.notification.NotificationType.INFORMATION
import com.intellij.notification.Notifications
import com.intellij.openapi.ui.Messages

private const val TITLE_PLUGIN_NAME = "Android Folder Opener"
private const val TITLE_ERROR = "Error"
private const val NOTIFICATIONS_GROUP_ID = "afo_group"

internal fun showErrorDialog(message: String) {
    Messages.showErrorDialog(message, TITLE_ERROR)
}

internal fun showInfoNotification(message: String) = showNotification(message, INFORMATION)

internal fun showErrorNotification(message: String) = showNotification(message, ERROR)

private fun showNotification(
    message: String,
    type: NotificationType,
) = Notifications.Bus.notify(
    Notification(NOTIFICATIONS_GROUP_ID, TITLE_PLUGIN_NAME, message, type),
)
