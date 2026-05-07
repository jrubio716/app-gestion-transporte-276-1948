package com.il.apptransporte.ui.screens.login

import androidx.compose.runtime.Composable
import com.il.apptransporte.ui.components.TestAction
import com.il.apptransporte.ui.components.TestScreen

@Composable
fun CoordinatorLoginScreen(
    onLogin: () -> Unit,
    onBack: () -> Unit
) {
    TestScreen(
        title = "HU8 - Iniciar sesión coordinador",
        actions = listOf(
            TestAction("Iniciar sesión", onLogin),
            TestAction("Volver", onBack)
        )
    )
}