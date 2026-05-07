package com.il.apptransporte.ui.screens.coordinator

import androidx.compose.runtime.Composable
import com.il.apptransporte.data.model.TransportOrder
import com.il.apptransporte.ui.components.TestAction
import com.il.apptransporte.ui.components.TestScreen

@Composable
fun TransportOrderMonitoringScreen(
    transportOrders: List<TransportOrder>,
    onGoToNotifications: () -> Unit,
    onGoToProfile: () -> Unit
) {
    TestScreen(
        title = "HU9 - Monitoreo de servicios\nServicios: ${transportOrders.size}",
        actions = listOf(
            TestAction("Notificaciones", onGoToNotifications),
            TestAction("Perfil", onGoToProfile)
        )
    )
}