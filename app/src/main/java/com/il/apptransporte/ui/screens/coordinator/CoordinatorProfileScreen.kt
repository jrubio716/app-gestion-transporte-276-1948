package com.il.apptransporte.ui.screens.coordinator

import androidx.compose.runtime.Composable
import com.il.apptransporte.data.model.User
import com.il.apptransporte.ui.components.TestAction
import com.il.apptransporte.ui.components.TestScreen

@Composable
fun CoordinatorProfileScreen(
    user: User,
    onLogout: () -> Unit,
    onGoToMonitoring: () -> Unit,
    onGoToNotifications: () -> Unit
) {
    TestScreen(
        title = "HU11 - Mi perfil coordinador\n${user.fullName}",
        actions = listOf(
            TestAction("Monitoreo", onGoToMonitoring),
            TestAction("Notificaciones", onGoToNotifications),
            TestAction("Cerrar sesión", onLogout)
        )
    )
}