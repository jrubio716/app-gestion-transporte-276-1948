package com.il.apptransporte.ui.screens.transportist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.il.apptransporte.data.model.User
import com.il.apptransporte.ui.components.TransportistBottomBar

@Composable
fun TransportistProfileScreen(
    user: User,
    onLogout: () -> Unit,
    onGoToServices: () -> Unit,
    onGoToSummary: () -> Unit,
    onGoToNotifications: () -> Unit
) {
    val primaryBlue = Color(0xFF003B73)
    val orange = Color(0xFFFF4B00)

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            TransportistBottomBar(
                selectedItem = "profile",
                onGoToServices = onGoToServices,
                onGoToSummary = onGoToSummary,
                onGoToNotifications = onGoToNotifications,
                onGoToProfile = {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp, vertical = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEAF2FF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Perfil",
                    tint = primaryBlue,
                    modifier = Modifier.size(58.dp)
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            ProfileInfoItem(
                icon = Icons.Outlined.Person,
                label = "Nombre:",
                value = user.fullName
            )

            Spacer(modifier = Modifier.height(10.dp))

            ProfileInfoItem(
                icon = Icons.Outlined.CreditCard,
                label = "DNI:",
                value = user.dni ?: "No registrado"
            )

            Spacer(modifier = Modifier.height(10.dp))

            ProfileInfoItem(
                icon = Icons.Outlined.LocalShipping,
                label = "Empresa transportista:",
                value = user.company ?: "No registrada"
            )

            Spacer(modifier = Modifier.height(10.dp))

            ProfileInfoItem(
                icon = Icons.Outlined.Email,
                label = "Correo:",
                value = user.email
            )

            Spacer(modifier = Modifier.height(10.dp))

            ProfileInfoItem(
                icon = Icons.Outlined.Phone,
                label = "Teléfono:",
                value = user.phone
            )

            Spacer(modifier = Modifier.height(10.dp))

            ProfileInfoItem(
                icon = Icons.Outlined.DirectionsCar,
                label = "Licencia de conducir:",
                value = user.license ?: "No registrada"
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = orange
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Logout,
                    contentDescription = "Cerrar sesión",
                    tint = orange
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Cerrar sesión",
                    color = orange,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun ProfileInfoItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    val primaryBlue = Color(0xFF003B73)
    val labelGray = Color(0xFF6B7280)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = primaryBlue,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(18.dp))

            Column {
                Text(
                    text = label,
                    color = labelGray,
                    fontSize = 13.sp
                )

                Text(
                    text = value,
                    color = primaryBlue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}