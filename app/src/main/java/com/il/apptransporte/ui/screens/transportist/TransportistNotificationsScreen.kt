package com.il.apptransporte.ui.screens.transportist

import androidx.compose.runtime.Composable
import com.il.apptransporte.data.model.AppNotification
import com.il.apptransporte.ui.components.TestAction
import com.il.apptransporte.ui.components.TestScreen

@Composable
fun TransportistNotificationsScreen(
    notifications: List<AppNotification>,
    onGoToServices: () -> Unit,
    onGoToSummary: () -> Unit,
    onGoToProfile: () -> Unit
) {
    TestScreen(
        title = "HU7 - Notificaciones transportista\nNotificaciones: ${notifications.size}",
        actions = listOf(
            TestAction("Servicios", onGoToServices),
            TestAction("Resumen", onGoToSummary),
            TestAction("Perfil", onGoToProfile)
        )
    )
}