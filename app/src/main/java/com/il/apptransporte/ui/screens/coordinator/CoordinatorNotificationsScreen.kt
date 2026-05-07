package com.il.apptransporte.ui.screens.coordinator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.il.apptransporte.data.model.AppNotification
import com.il.apptransporte.ui.components.CoordinatorBottomBar

private val PrimaryBlue = Color(0xFF003B73)
private val LightBlue = Color(0xFFEAF2FF)
private val Orange = Color(0xFFFF6B00)
private val LightOrange = Color(0xFFFFF1E7)
private val Green = Color(0xFF1B7F3A)
private val LightGreen = Color(0xFFE8F5E9)
private val CardBorder = Color(0xFFE5E7EB)
private val TextPrimary = Color(0xFF1F2937)
private val TextSecondary = Color(0xFF6B7280)

@Composable
fun CoordinatorNotificationsScreen(
    notifications: List<AppNotification>,
    onGoToMonitoring: () -> Unit,
    onGoToProfile: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }

    val unreadCount = notifications.count { !it.isRead }

    val filteredNotifications = when (selectedTab) {
        1 -> notifications.filter { !it.isRead }
        else -> notifications
    }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            CoordinatorBottomBar(
                selectedItem = "notifications",
                onGoToMonitoring = onGoToMonitoring,
                onGoToNotifications = {},
                onGoToProfile = onGoToProfile
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Text(
                text = "Notificaciones",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Seguimiento de cambios en los servicios activos",
                fontSize = 12.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(14.dp))

            CoordinatorUnreadSummaryCard(unreadCount = unreadCount)

            Spacer(modifier = Modifier.height(16.dp))

            CoordinatorNotificationTabs(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            Spacer(modifier = Modifier.height(14.dp))

            if (filteredNotifications.isEmpty()) {
                EmptyCoordinatorNotificationsMessage()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredNotifications) { notification ->
                        CoordinatorNotificationCard(notification = notification)
                    }
                }
            }
        }
    }
}

@Composable
private fun CoordinatorUnreadSummaryCard(
    unreadCount: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = LightBlue),
        border = BorderStroke(1.dp, Color(0xFFBBD7FF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.NotificationsNone,
                    contentDescription = "Notificaciones",
                    tint = PrimaryBlue,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Tienes $unreadCount notificaciones sin leer",
                color = PrimaryBlue,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CoordinatorNotificationTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CoordinatorNotificationTabItem(
                title = "Todas",
                selected = selectedTab == 0,
                onClick = { onTabSelected(0) },
                modifier = Modifier.weight(1f)
            )

            CoordinatorNotificationTabItem(
                title = "Sin leer",
                selected = selectedTab == 1,
                onClick = { onTabSelected(1) },
                modifier = Modifier.weight(1f)
            )
        }

        HorizontalDivider(color = CardBorder)
    }
}

@Composable
private fun CoordinatorNotificationTabItem(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 13.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (selected) PrimaryBlue else TextSecondary,
            modifier = Modifier.padding(vertical = 6.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(if (selected) PrimaryBlue else Color.Transparent)
        )
    }
}

@Composable
private fun CoordinatorNotificationCard(
    notification: AppNotification
) {
    val iconData = getCoordinatorNotificationIconData(notification)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, CardBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(iconData.backgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = iconData.icon,
                        contentDescription = null,
                        tint = iconData.tintColor,
                        modifier = Modifier.size(23.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = notification.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryBlue,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = notification.time,
                            fontSize = 12.sp,
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = notification.message,
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    if (!notification.isRead) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(Orange)
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = "Sin leer",
                                fontSize = 12.sp,
                                color = TextSecondary
                            )
                        }
                    } else {
                        Text(
                            text = "Leída",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyCoordinatorNotificationsMessage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No hay notificaciones para mostrar.",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private data class CoordinatorNotificationIconData(
    val icon: ImageVector,
    val tintColor: Color,
    val backgroundColor: Color
)

private fun getCoordinatorNotificationIconData(
    notification: AppNotification
): CoordinatorNotificationIconData {
    val text = "${notification.title} ${notification.message}"

    return when {
        text.contains("retirada", ignoreCase = true) ||
                text.contains("mercadería", ignoreCase = true) ||
                text.contains("mercaderia", ignoreCase = true) -> {
            CoordinatorNotificationIconData(
                icon = Icons.Outlined.CheckCircle,
                tintColor = Green,
                backgroundColor = LightGreen
            )
        }

        text.contains("ruta", ignoreCase = true) ||
                text.contains("transportista", ignoreCase = true) -> {
            CoordinatorNotificationIconData(
                icon = Icons.Outlined.LocalShipping,
                tintColor = PrimaryBlue,
                backgroundColor = LightBlue
            )
        }

        text.contains("hora", ignoreCase = true) ||
                text.contains("retiro", ignoreCase = true) -> {
            CoordinatorNotificationIconData(
                icon = Icons.Outlined.AccessTime,
                tintColor = Orange,
                backgroundColor = LightOrange
            )
        }

        else -> {
            CoordinatorNotificationIconData(
                icon = Icons.Outlined.NotificationsNone,
                tintColor = PrimaryBlue,
                backgroundColor = LightBlue
            )
        }
    }
}
