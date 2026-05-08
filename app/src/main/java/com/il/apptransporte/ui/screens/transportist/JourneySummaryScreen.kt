package com.il.apptransporte.ui.screens.transportist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.PendingActions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.il.apptransporte.data.model.TransportOrder
import com.il.apptransporte.data.model.TransportOrderStatus
import com.il.apptransporte.ui.components.StatusChip
import com.il.apptransporte.ui.components.TransportistBottomBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// ─────────────────────────────────────────────
// HU6 – Resumen de Jornada (Transportista)
// Muestra métricas del día + lista de servicios
// ─────────────────────────────────────────────

@Composable
fun JourneySummaryScreen(
    transportOrders: List<TransportOrder>,
    onGoToServices: () -> Unit,
    onGoToNotifications: () -> Unit,
    onGoToProfile: () -> Unit
) {
    val primaryBlue = Color(0xFF003B73)
    val bgGray      = Color(0xFFF9FAFB)

    val completed = remember(transportOrders) {
        transportOrders.count { it.status == TransportOrderStatus.COMPLETED }
    }
    val pending = remember(transportOrders) {
        transportOrders.count { it.status != TransportOrderStatus.COMPLETED }
    }
    val total = transportOrders.size

    val todayLabel = remember {
        SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy", Locale("es", "PE"))
            .format(Date())
            .replaceFirstChar { it.uppercase() }
    }

    Scaffold(
        bottomBar = {
            TransportistBottomBar(
                selectedItem = "summary",
                onGoToServices = onGoToServices,
                onGoToSummary = {},
                onGoToNotifications = onGoToNotifications,
                onGoToProfile = onGoToProfile
            )
        },
        containerColor = bgGray
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            // ── Header azul con título y fecha ─────────────────────────
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(primaryBlue)
                        .padding(horizontal = 16.dp, vertical = 20.dp)
                ) {
                    Column {
                        Text(
                            text = "Mis entregas del día",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = todayLabel,
                            color = Color.White.copy(alpha = 0.75f),
                            fontSize = 13.sp
                        )
                    }
                }
            }

            // ── Tarjetas de métricas ───────────────────────────────────
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    JourneyMetricCard(
                        modifier   = Modifier.weight(1f),
                        icon       = Icons.Outlined.CheckCircle,
                        value      = completed.toString(),
                        label      = "Completadas",
                        iconColor  = Color(0xFF1B7F3A),
                        valueColor = Color(0xFF1B7F3A),
                        bgColor    = Color(0xFFDDF5E5)
                    )
                    JourneyMetricCard(
                        modifier   = Modifier.weight(1f),
                        icon       = Icons.Outlined.PendingActions,
                        value      = pending.toString(),
                        label      = "Pendientes",
                        iconColor  = Color(0xFF9A5B00),
                        valueColor = Color(0xFF9A5B00),
                        bgColor    = Color(0xFFFFF4D6)
                    )
                    JourneyMetricCard(
                        modifier   = Modifier.weight(1f),
                        icon       = Icons.Outlined.Inventory2,
                        value      = total.toString(),
                        label      = "Total",
                        iconColor  = Color(0xFF003B73),
                        valueColor = Color(0xFF003B73),
                        bgColor    = Color(0xFFE2F1FF)
                    )
                }
            }

            // ── Título de sección ──────────────────────────────────────
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(4.dp)
                            .height(18.dp)
                            .background(
                                color = primaryBlue,
                                shape = RoundedCornerShape(2.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Detalle de servicios",
                        color = primaryBlue,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
            }

            // ── Lista de servicios o estado vacío ──────────────────────
            if (transportOrders.isEmpty()) {
                item { JourneySummaryEmpty() }
            } else {
                items(transportOrders) { order ->
                    JourneySummaryOrderItem(
                        transportOrder = order,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
            }
        }
    }
}

// ── Tarjeta de métrica individual ─────────────────────────────────────────────

@Composable
private fun JourneyMetricCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    value: String,
    label: String,
    iconColor: Color,
    valueColor: Color,
    bgColor: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconColor,
                modifier = Modifier.size(26.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = valueColor
            )
            Text(
                text = label,
                fontSize = 11.sp,
                color = valueColor,
                lineHeight = 13.sp
            )
        }
    }
}

// ── Item compacto de la lista ──────────────────────────────────────────────────

@Composable
private fun JourneySummaryOrderItem(
    transportOrder: TransportOrder,
    modifier: Modifier = Modifier
) {
    val primaryBlue = Color(0xFF003B73)
    val labelGray   = Color(0xFF6B7280)
    val borderGray  = Color(0xFFE5E7EB)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            // Código + badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = transportOrder.code,
                    color = primaryBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                StatusChip(status = transportOrder.status)
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = borderGray
            )

            // Cliente
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Cliente:  ", color = labelGray,   fontSize = 12.sp)
                Text(text = transportOrder.client,   color = primaryBlue, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(3.dp))

            // Almacén
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Almacén: ", color = labelGray,   fontSize = 12.sp)
                Text(text = transportOrder.warehouse, color = primaryBlue, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
            }
        }
    }
}

// ── Estado vacío ───────────────────────────────────────────────────────────────

@Composable
private fun JourneySummaryEmpty() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.Inventory2,
            contentDescription = null,
            tint = Color(0xFFD1D5DB),
            modifier = Modifier.size(56.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Sin servicios para hoy", color = Color(0xFF6B7280), fontSize = 15.sp, fontWeight = FontWeight.Medium)
        Text(text = "No tienes entregas asignadas.", color = Color(0xFF9CA3AF), fontSize = 13.sp)
    }
}
