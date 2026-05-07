package com.il.apptransporte.ui.screens.access

import androidx.compose.runtime.Composable
import com.il.apptransporte.ui.components.TestAction
import com.il.apptransporte.ui.components.TestScreen

@Composable
fun AccessSelectionScreen(
    onTransportistAccess: () -> Unit,
    onCoordinatorAccess: () -> Unit
) {
    TestScreen(
        title = "HU12 - Selección de acceso",
        actions = listOf(
            TestAction("Acceso transportista", onTransportistAccess),
            TestAction("Acceso interno", onCoordinatorAccess)
        )
    )
}