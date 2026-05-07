package com.il.apptransporte.ui.screens.coordinator

import androidx.compose.runtime.Composable
import com.il.apptransporte.data.model.AppNotification
import com.il.apptransporte.ui.components.TestAction
import com.il.apptransporte.ui.components.TestScreen

@Composable
fun CoordinatorNotificationsScreen(
    notifications: List<AppNotification>,
    onGoToMonitoring: () -> Unit,
    onGoToProfile: () -> Unit
) {
    TestScreen(
        title = "HU10 - Notificaciones coordinador\nNotificaciones: ${notifications.size}",
        actions = listOf(
            TestAction("Monitoreo", onGoToMonitoring),
            TestAction("Perfil", onGoToProfile)
        )
    )
}