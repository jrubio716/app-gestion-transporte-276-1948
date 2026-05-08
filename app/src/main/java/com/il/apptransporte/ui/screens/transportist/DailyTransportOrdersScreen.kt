package com.il.apptransporte.ui.screens.transportist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.il.apptransporte.data.model.TransportOrder
import com.il.apptransporte.ui.components.TransportOrderCard
import com.il.apptransporte.ui.components.TransportistBottomBar

@Composable
fun DailyTransportOrdersScreen(
    transportOrders: List<TransportOrder>,
    onUpdateStatus: (TransportOrder) -> Unit,
    onGoToSummary: () -> Unit,
    onGoToNotifications: () -> Unit,
    onGoToProfile: () -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            TransportistBottomBar(
                selectedItem = "services",
                onGoToServices = {},
                onGoToSummary = onGoToSummary,
                onGoToNotifications = onGoToNotifications,
                onGoToProfile = onGoToProfile
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Mis servicios del día",
                color = Color(0xFF003B73),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Martes, 20 de mayo de 2025",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                contentPadding = PaddingValues(bottom = 14.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(transportOrders) { order ->
                    TransportOrderCard(
                        transportOrder = order,
                        onUpdateStatus = {
                            onUpdateStatus(order)
                        }
                    )
                }
            }
        }
    }
}