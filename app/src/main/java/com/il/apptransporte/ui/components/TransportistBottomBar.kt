package com.il.apptransporte.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TransportistBottomBar(
    selectedItem: String,
    onGoToServices: () -> Unit,
    onGoToSummary: () -> Unit,
    onGoToNotifications: () -> Unit,
    onGoToProfile: () -> Unit
) {
    val primaryBlue = Color(0xFF003B73)
    val unselectedGray = Color(0xFF6B7280)

    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            selected = selectedItem == "services",
            onClick = onGoToServices,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.LocalShipping,
                    contentDescription = "Servicios"
                )
            },
            label = { Text("Servicios") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryBlue,
                selectedTextColor = primaryBlue,
                indicatorColor = Color(0xFFEAF2FF),
                unselectedIconColor = unselectedGray,
                unselectedTextColor = unselectedGray
            )
        )

        NavigationBarItem(
            selected = selectedItem == "summary",
            onClick = onGoToSummary,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Inventory2,
                    contentDescription = "Entregas"
                )
            },
            label = { Text("Entregas") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryBlue,
                selectedTextColor = primaryBlue,
                indicatorColor = Color(0xFFEAF2FF),
                unselectedIconColor = unselectedGray,
                unselectedTextColor = unselectedGray
            )
        )

        NavigationBarItem(
            selected = selectedItem == "notifications",
            onClick = onGoToNotifications,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notificaciones"
                )
            },
            label = { Text("Notificaciones") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryBlue,
                selectedTextColor = primaryBlue,
                indicatorColor = Color(0xFFEAF2FF),
                unselectedIconColor = unselectedGray,
                unselectedTextColor = unselectedGray
            )
        )

        NavigationBarItem(
            selected = selectedItem == "profile",
            onClick = onGoToProfile,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Perfil"
                )
            },
            label = { Text("Perfil") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryBlue,
                selectedTextColor = primaryBlue,
                indicatorColor = Color(0xFFEAF2FF),
                unselectedIconColor = unselectedGray,
                unselectedTextColor = unselectedGray
            )
        )
    }
}