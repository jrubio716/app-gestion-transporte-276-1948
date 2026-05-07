package com.il.apptransporte.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Assessment
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
fun CoordinatorBottomBar(
    selectedItem: String,
    onGoToMonitoring: () -> Unit,
    onGoToNotifications: () -> Unit,
    onGoToProfile: () -> Unit
) {
    val primaryBlue = Color(0xFF003B73)
    val orange = Color(0xFFFF4B00)
    val unselectedGray = Color(0xFF5F6368)

    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            selected = selectedItem == "monitoring",
            onClick = onGoToMonitoring,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Assessment,
                    contentDescription = "Monitoreo"
                )
            },
            label = {
                Text(text = "Monitoreo")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryBlue,
                selectedTextColor = primaryBlue,
                indicatorColor = Color.Transparent,
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
            label = {
                Text(text = "Notificaciones")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = orange,
                selectedTextColor = orange,
                indicatorColor = Color.Transparent,
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
            label = {
                Text(text = "Perfil")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = primaryBlue,
                selectedTextColor = primaryBlue,
                indicatorColor = Color.Transparent,
                unselectedIconColor = unselectedGray,
                unselectedTextColor = unselectedGray
            )
        )
    }
}