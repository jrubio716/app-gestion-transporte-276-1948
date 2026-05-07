package com.il.apptransporte.ui.screens.transportist

import androidx.compose.runtime.Composable
import com.il.apptransporte.data.model.TransportOrder
import com.il.apptransporte.ui.components.TestAction
import com.il.apptransporte.ui.components.TestScreen

@Composable
fun JourneySummaryScreen(
    transportOrders: List<TransportOrder>,
    onGoToServices: () -> Unit,
    onGoToNotifications: () -> Unit,
    onGoToProfile: () -> Unit
) {
    TestScreen(
        title = "HU6 - Resumen de jornada\nServicios: ${transportOrders.size}",
        actions = listOf(
            TestAction("Servicios", onGoToServices),
            TestAction("Notificaciones", onGoToNotifications),
            TestAction("Perfil", onGoToProfile)
        )
    )
}