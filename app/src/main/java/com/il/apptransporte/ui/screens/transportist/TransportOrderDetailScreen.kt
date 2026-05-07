package com.il.apptransporte.ui.screens.transportist

import androidx.compose.runtime.Composable
import com.il.apptransporte.data.model.TransportOrder
import com.il.apptransporte.data.model.TransportOrderStatus
import com.il.apptransporte.ui.components.TestAction
import com.il.apptransporte.ui.components.TestScreen

@Composable
fun TransportOrderDetailScreen(
    transportOrder: TransportOrder?,
    onBack: () -> Unit,
    onSaveStatus: (TransportOrderStatus) -> Unit
) {
    TestScreen(
        title = "HU4 - Actualizar estado\n${transportOrder?.code ?: "Sin servicio"}",
        actions = listOf(
            TestAction("Guardar estado completado") {
                onSaveStatus(TransportOrderStatus.COMPLETED)
            },
            TestAction("Volver", onBack)
        )
    )
}