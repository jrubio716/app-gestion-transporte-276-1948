package com.il.apptransporte.ui.screens.transportist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.il.apptransporte.ui.components.TransportistBottomBar

@Composable
fun EvidenceScreen(
    transportOrder: TransportOrder?,
    onBack: () -> Unit,
    onSaveEvidence: () -> Unit
) {
    val primaryBlue = Color(0xFF003B73)
    val borderGray = Color(0xFFE5E7EB)

    var observations by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            TransportistBottomBar(
                selectedItem = "summary",
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
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(primaryBlue)
                        .padding(horizontal = 10.dp, vertical = 18.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Volver",
                                tint = Color.White
                            )
                        }

                        Text(
                            text = "Evidencia de entrega",
                            color = Color.White,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, borderGray),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            EvidenceInfoRow(
                                icon = Icons.Outlined.LocalShipping,
                                label = "Servicio:",
                                value = transportOrder.code
                            )

                            EvidenceInfoRow(
                                icon = Icons.Outlined.Home,
                                label = "Almacén de retiro:",
                                value = transportOrder.warehouse
                            )

                            EvidenceInfoRow(
                                icon = Icons.Outlined.Person,
                                label = "Cliente:",
                                value = transportOrder.client
                            )

                            EvidenceInfoRow(
                                icon = Icons.Outlined.Event,
                                label = "Fecha y hora:",
                                value = "06/05/2026 ${transportOrder.scheduledTime}"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(165.dp)
                            .border(
                                width = 2.dp,
                                color = primaryBlue,
                                shape = RoundedCornerShape(14.dp)
                            )
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.PhotoCamera,
                                contentDescription = "Adjuntar evidencia",
                                tint = primaryBlue,
                                modifier = Modifier.size(58.dp)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Tomar foto o adjuntar evidencia",
                                color = primaryBlue,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        EvidencePlaceholder(modifier = Modifier.weight(1f))
                        EvidencePlaceholder(modifier = Modifier.weight(1f))
                        EvidencePlaceholder(modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Observaciones (opcional)",
                        color = primaryBlue,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = observations,
                        onValueChange = { observations = it },
                        placeholder = {
                            Text(text = "Escribe tus observaciones...")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryBlue,
                            unfocusedBorderColor = borderGray
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onSaveEvidence,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryBlue,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Guardar evidencia",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EvidenceInfoRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    val primaryBlue = Color(0xFF003B73)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = primaryBlue,
            modifier = Modifier.size(23.dp)
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
            color = Color(0xFF111827),
            modifier = Modifier.weight(1.2f)
        )
    }
}

@Composable
private fun EvidencePlaceholder(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(92.dp)
            .background(
                color = Color(0xFFF3F4F6),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFE5E7EB),
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Image,
            contentDescription = "Imagen de evidencia",
            tint = Color(0xFFBDBDBD),
            modifier = Modifier.size(38.dp)
        )
    }
}