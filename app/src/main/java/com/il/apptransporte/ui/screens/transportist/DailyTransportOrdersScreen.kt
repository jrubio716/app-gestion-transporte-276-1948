package com.il.apptransporte.ui.screens.transportist

import androidx.compose.runtime.Composable
import com.il.apptransporte.data.model.TransportOrder
import com.il.apptransporte.ui.components.TestAction
import com.il.apptransporte.ui.components.TestScreen

@Composable
fun DailyTransportOrdersScreen(
    transportOrders: List<TransportOrder>,
    onUpdateStatus: (TransportOrder) -> Unit,
    onGoToSummary: () -> Unit,
    onGoToNotifications: () -> Unit,
    onGoToProfile: () -> Unit
) {
    val firstOrder = transportOrders.firstOrNull()

    val actions = mutableListOf(
        TestAction("Resumen", onGoToSummary),
        TestAction("Notificaciones", onGoToNotifications),
        TestAction("Perfil", onGoToProfile)
    )

    if (firstOrder != null) {
        actions.add(
            0,
            TestAction("Actualizar estado") {
                onUpdateStatus(firstOrder)
            }
        )
    }

    TestScreen(
        title = "HU3 - Mis servicios del día",
        actions = actions
    )
}