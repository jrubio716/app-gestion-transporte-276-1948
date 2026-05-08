package com.il.apptransporte.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.il.apptransporte.R
import com.il.apptransporte.ui.components.LoginTextField
import com.il.apptransporte.ui.components.PrimaryButton
import kotlinx.coroutines.launch

@Composable
fun CoordinatorLoginScreen(
    onLogin: () -> Unit,
    onBack: () -> Unit
) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = Color.White,
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_interlogistics),
                contentDescription = "Logo InterLogistics",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(76.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Coordinador",
                color = Color(0xFF003B73),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "de operaciones",
                color = Color(0xFF003B73),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(28.dp))

            LoginTextField(
                value = user,
                onValueChange = { user = it },
                label = "Usuario o correo",
                placeholder = "Ingresa tu usuario o correo",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            LoginTextField(
                value = password,
                onValueChange = { password = it },
                label = "Contraseña",
                placeholder = "Ingresa tu contraseña",
                isPassword = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(28.dp))

            PrimaryButton(
                text = "Iniciar sesión",
                onClick = {
                    if (user.isBlank() || password.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Completa todos los campos")
                        }
                    } else {
                        onLogin()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(
                onClick = onBack
            ) {
                Text(
                    text = "Volver",
                    color = Color(0xFF003B73),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}