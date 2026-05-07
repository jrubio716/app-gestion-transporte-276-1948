package com.il.apptransporte.ui.screens.transportist

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun TransportOrderDetailScreen(
    transportOrder: TransportOrder?,
    onBack: () -> Unit,
    onSaveStatus: (TransportOrderStatus) -> Unit
) {
    val primaryBlue = Color(0xFF003B73)
    val orange = Color(0xFFFF4B00)
    val borderGray = Color(0xFFE5E7EB)

    var selectedStatus by remember {
        mutableStateOf(transportOrder?.status ?: TransportOrderStatus.ASSIGNED)
    }

    LaunchedEffect(transportOrder) {
        selectedStatus = transportOrder?.status ?: TransportOrderStatus.ASSIGNED
    }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            TransportistBottomBar(
                selectedItem = "services",
                onGoToServices = onBack,
                onGoToSummary = {},
                onGoToNotifications = {},
                onGoToProfile = {}
            )
        }
    ) { paddingValues ->
        if (transportOrder == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No se encontró la orden de transporte.")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = primaryBlue
                        )
                    }

                    Text(
                        text = "Detalle del servicio",
                        color = primaryBlue,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, borderGray),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        DetailRow(
                            icon = Icons.Outlined.Description,
                            label = "Código de servicio",
                            value = transportOrder.code
                        )

                        DetailRow(
                            icon = Icons.Outlined.Home,
                            label = "Almacén",
                            value = transportOrder.warehouse
                        )

                        DetailRow(
                            icon = Icons.Outlined.LocationOn,
                            label = "Dirección de almacén",
                            value = transportOrder.warehouseAddress
                        )

                        DetailRow(
                            icon = Icons.Outlined.Person,
                            label = "Cliente",
                            value = transportOrder.client
                        )

                        DetailRow(
                            icon = Icons.Outlined.LocationOn,
                            label = "Dirección del cliente",
                            value = transportOrder.clientAddress
                        )

                        DetailRow(
                            icon = Icons.Outlined.LocalShipping,
                            label = "Tipo de vehículo",
                            value = transportOrder.vehicleType
                        )

                        DetailRow(
                            icon = Icons.Outlined.ConfirmationNumber,
                            label = "Placa",
                            value = transportOrder.plate
                        )

                        DetailRow(
                            icon = Icons.Outlined.Description,
                            label = "Guía / Documento",
                            value = transportOrder.documentCode
                        )

                        DetailRow(
                            icon = Icons.Outlined.Schedule,
                            label = "Hora programada",
                            value = transportOrder.scheduledTime
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Schedule,
                                contentDescription = null,
                                tint = primaryBlue,
                                modifier = Modifier.size(22.dp)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = "Estado actual",
                                color = primaryBlue,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )

                            StatusChip(status = transportOrder.status)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, borderGray),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Text(
                            text = "Actualizar estado",
                            color = primaryBlue,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TransportOrderStatus.values().forEach { status ->
                            StatusOptionRow(
                                status = status,
                                selected = selectedStatus == status,
                                selectedColor = orange,
                                onClick = {
                                    selectedStatus = status
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                onSaveStatus(selectedStatus)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = primaryBlue,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Guardar estado",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    val primaryBlue = Color(0xFF003B73)
    val valueColor = Color(0xFF111827)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = primaryBlue,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = label,
            color = primaryBlue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            color = valueColor,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun StatusOptionRow(
    status: TransportOrderStatus,
    selected: Boolean,
    selectedColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = selectedColor
            )
        )

        Text(
            text = status.label,
            fontSize = 15.sp
        )
    }
}