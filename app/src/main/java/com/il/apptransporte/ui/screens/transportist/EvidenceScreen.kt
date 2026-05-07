package com.il.apptransporte.ui.screens.transportist

import androidx.compose.runtime.Composable
import com.il.apptransporte.data.model.TransportOrder
import com.il.apptransporte.ui.components.TestAction
import com.il.apptransporte.ui.components.TestScreen

@Composable
fun EvidenceScreen(
    transportOrder: TransportOrder?,
    onBack: () -> Unit,
    onSaveEvidence: () -> Unit
) {
    TestScreen(
        title = "HU5 - Registrar evidencia\n${transportOrder?.code ?: "Sin servicio"}",
        actions = listOf(
            TestAction("Guardar evidencia", onSaveEvidence),
            TestAction("Volver", onBack)
        )
    )
}