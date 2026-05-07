package com.il.apptransporte.ui.screens.login

import androidx.compose.runtime.Composable
import com.il.apptransporte.ui.components.TestAction
import com.il.apptransporte.ui.components.TestScreen

@Composable
fun TransportistLoginScreen(
    onLogin: () -> Unit,
    onBack: () -> Unit
) {
    TestScreen(
        title = "HU1 - Iniciar sesión transportista",
        actions = listOf(
            TestAction("Iniciar sesión", onLogin),
            TestAction("Volver", onBack)
        )
    )
}