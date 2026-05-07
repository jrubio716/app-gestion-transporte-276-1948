package com.il.apptransporte.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.il.apptransporte.data.model.TransportOrder

@Composable
fun TransportOrderCard(
    transportOrder: TransportOrder,
    modifier: Modifier = Modifier,
    showCoordinatorInfo: Boolean = false,
    onUpdateStatus: (() -> Unit)? = null,
    onRegisterEvidence: (() -> Unit)? = null
) {
    val primaryBlue = Color(0xFF003B73)
    val labelGray = Color(0xFF6B7280)
    val borderGray = Color(0xFFE5E7EB)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, borderGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = transportOrder.code,
                    color = primaryBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                StatusChip(status = transportOrder.status)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TransportOrderInfoItem(
                        icon = Icons.Outlined.Home,
                        label = "Almacén de retiro",
                        value = transportOrder.warehouse,
                        valueColor = primaryBlue,
                        labelColor = labelGray
                    )

                    TransportOrderInfoItem(
                        icon = Icons.Outlined.Person,
                        label = "Cliente",
                        value = transportOrder.client,
                        valueColor = primaryBlue,
                        labelColor = labelGray
                    )

                    TransportOrderInfoItem(
                        icon = Icons.Outlined.LocationOn,
                        label = "Dirección de entrega",
                        value = transportOrder.clientAddress,
                        valueColor = primaryBlue,
                        labelColor = labelGray
                    )

                    if (showCoordinatorInfo) {
                        TransportOrderInfoItem(
                            icon = Icons.Outlined.Person,
                            label = "Transportista",
                            value = transportOrder.transportist,
                            valueColor = primaryBlue,
                            labelColor = labelGray
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(112.dp)
                        .background(borderGray)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier = Modifier.width(98.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TransportOrderInfoItem(
                        icon = Icons.Outlined.LocalShipping,
                        label = "Vehículo",
                        value = transportOrder.vehicleType,
                        valueColor = primaryBlue,
                        labelColor = labelGray
                    )

                    TransportOrderInfoItem(
                        icon = Icons.Outlined.ConfirmationNumber,
                        label = "Placa",
                        value = transportOrder.plate,
                        valueColor = primaryBlue,
                        labelColor = labelGray
                    )
                }
            }

            if (onUpdateStatus != null || onRegisterEvidence != null) {
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (onUpdateStatus != null) {
                        Button(
                            onClick = onUpdateStatus,
                            modifier = Modifier
                                .weight(1f)
                                .height(42.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = primaryBlue,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Actualizar estado",
                                fontSize = 12.sp
                            )
                        }
                    }

                    if (onRegisterEvidence != null) {
                        OutlinedButton(
                            onClick = onRegisterEvidence,
                            modifier = Modifier
                                .weight(1f)
                                .height(42.dp),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, primaryBlue),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = primaryBlue
                            )
                        ) {
                            Text(
                                text = "Registrar evidencia",
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TransportOrderInfoItem(
    icon: ImageVector,
    label: String,
    value: String,
    valueColor: Color,
    labelColor: Color
) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = labelColor,
            modifier = Modifier
                .size(21.dp)
                .padding(top = 2.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = label,
                color = labelColor,
                fontSize = 11.sp,
                lineHeight = 12.sp
            )

            Text(
                text = value,
                color = valueColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 14.sp
            )
        }
    }
}