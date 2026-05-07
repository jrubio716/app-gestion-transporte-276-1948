package com.il.apptransporte.ui.screens.transportist

import androidx.compose.runtime.Composable
import com.il.apptransporte.data.model.User
import com.il.apptransporte.ui.components.TestAction
import com.il.apptransporte.ui.components.TestScreen

@Composable
fun TransportistProfileScreen(
    user: User,
    onLogout: () -> Unit,
    onGoToServices: () -> Unit,
    onGoToSummary: () -> Unit,
    onGoToNotifications: () -> Unit
) {
    TestScreen(
        title = "HU2 - Mi perfil transportista\n${user.fullName}",
        actions = listOf(
            TestAction("Servicios", onGoToServices),
            TestAction("Resumen", onGoToSummary),
            TestAction("Notificaciones", onGoToNotifications),
            TestAction("Cerrar sesión", onLogout)
        )
    )
}