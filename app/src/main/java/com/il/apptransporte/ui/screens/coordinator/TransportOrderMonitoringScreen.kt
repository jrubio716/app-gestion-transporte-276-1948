package com.il.apptransporte.ui.screens.coordinator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Assessment
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.il.apptransporte.data.model.TransportOrder
import com.il.apptransporte.data.model.TransportOrderStatus
import com.il.apptransporte.ui.components.CoordinatorBottomBar
import com.il.apptransporte.ui.components.TransportOrderCard

// ─────────────────────────────────────────────────────────────────────────────
// HU9 – Monitoreo de Entregas (Coordinador de operaciones)
// Panel de control con filtros por transportista/estado/cliente + búsqueda
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TransportOrderMonitoringScreen(
    transportOrders: List<TransportOrder>,
    onGoToNotifications: () -> Unit,
    onGoToProfile: () -> Unit
) {
    val primaryBlue = Color(0xFF003B73)
    val bgGray      = Color(0xFFF9FAFB)
    val borderGray  = Color(0xFFE5E7EB)

    // ── Estado de filtros ──────────────────────────────────────────────────────
    var searchQuery        by remember { mutableStateOf("") }
    var selectedTransportist by remember { mutableStateOf("Todos") }
    var selectedStatus     by remember { mutableStateOf("Todos") }
    var selectedClient     by remember { mutableStateOf("Todos") }

    // Opciones dinámicas a partir de los datos reales
    val transportistOptions = remember(transportOrders) {
        listOf("Todos") + transportOrders.map { it.transportist }.distinct().sorted()
    }
    val statusOptions = remember {
        listOf("Todos") + TransportOrderStatus.entries.map { it.label }
    }
    val clientOptions = remember(transportOrders) {
        listOf("Todos") + transportOrders.map { it.client }.distinct().sorted()
    }

    // ── Filtrado en tiempo real ────────────────────────────────────────────────
    val filteredOrders = remember(transportOrders, searchQuery, selectedTransportist, selectedStatus, selectedClient) {
        transportOrders.filter { order ->
            val matchTransportist = selectedTransportist == "Todos" ||
                    order.transportist.equals(selectedTransportist, ignoreCase = true)
            val matchStatus = selectedStatus == "Todos" ||
                    order.status.label.equals(selectedStatus, ignoreCase = true)
            val matchClient = selectedClient == "Todos" ||
                    order.client.equals(selectedClient, ignoreCase = true)
            val matchSearch = searchQuery.isBlank() ||
                    order.code.contains(searchQuery, ignoreCase = true) ||
                    order.client.contains(searchQuery, ignoreCase = true) ||
                    order.plate.contains(searchQuery, ignoreCase = true) ||
                    order.transportist.contains(searchQuery, ignoreCase = true)
            matchTransportist && matchStatus && matchClient && matchSearch
        }
    }

    Scaffold(
        bottomBar = {
            CoordinatorBottomBar(
                selectedItem = "monitoring",
                onGoToMonitoring = {},
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

            // ── Header ──────────────────────────────────────────────────
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(primaryBlue)
                        .padding(horizontal = 16.dp, vertical = 18.dp)
                ) {
                    Text(
                        text = "Monitoreo de servicios",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // ── Panel de filtros (sticky via Surface) ────────────────────
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    shadowElevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Fila de dropdowns
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            MonitoringDropdown(
                                modifier    = Modifier.weight(1f),
                                label       = "Transportista",
                                selected    = selectedTransportist,
                                options     = transportistOptions,
                                onSelected  = { selectedTransportist = it },
                                primaryBlue = primaryBlue,
                                borderGray  = borderGray
                            )
                            MonitoringDropdown(
                                modifier    = Modifier.weight(1f),
                                label       = "Estado",
                                selected    = selectedStatus,
                                options     = statusOptions,
                                onSelected  = { selectedStatus = it },
                                primaryBlue = primaryBlue,
                                borderGray  = borderGray
                            )
                            MonitoringDropdown(
                                modifier    = Modifier.weight(1f),
                                label       = "Cliente",
                                selected    = selectedClient,
                                options     = clientOptions,
                                onSelected  = { selectedClient = it },
                                primaryBlue = primaryBlue,
                                borderGray  = borderGray
                            )
                        }

                        // Barra de búsqueda
                        MonitoringSearchBar(
                            query        = searchQuery,
                            onQueryChange = { searchQuery = it },
                            onClear      = { searchQuery = "" },
                            borderGray   = borderGray
                        )
                    }
                }
            }

            // ── Contador de servicios ─────────────────────────────────────
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Assessment,
                        contentDescription = null,
                        tint = primaryBlue,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    val label = if (filteredOrders.size == 1) "Servicio activo" else "Servicios activos"
                    Text(
                        text = "$label (${filteredOrders.size})",
                        color = primaryBlue,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // ── Lista de servicios ─────────────────────────────────────────
            if (filteredOrders.isEmpty()) {
                item { MonitoringEmptyState() }
            } else {
                items(filteredOrders) { order ->
                    TransportOrderCard(
                        transportOrder = order,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                        showCoordinatorInfo = true
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
            }
        }
    }
}

// ── Dropdown de filtro ────────────────────────────────────────────────────────

@Composable
private fun MonitoringDropdown(
    modifier: Modifier = Modifier,
    label: String,
    selected: String,
    options: List<String>,
    onSelected: (String) -> Unit,
    primaryBlue: Color,
    borderGray: Color
) {
    var expanded by remember { mutableStateOf(false) }
    val labelGray = Color(0xFF6B7280)

    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 10.sp,
            color = labelGray,
            modifier = Modifier.padding(bottom = 3.dp)
        )
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .border(1.dp, borderGray, RoundedCornerShape(8.dp))
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .clickable { expanded = true }
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = selected,
                    fontSize = 11.sp,
                    color = primaryBlue,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Outlined.ExpandMore,
                    contentDescription = "Expandir",
                    tint = labelGray,
                    modifier = Modifier.size(16.dp)
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option,
                                fontSize = 13.sp,
                                color = if (option == selected) primaryBlue else Color(0xFF1F2937),
                                fontWeight = if (option == selected) FontWeight.SemiBold else FontWeight.Normal
                            )
                        },
                        onClick = {
                            onSelected(option)
                            expanded = false
                        }
                    )
                    if (option == "Todos") {
                        HorizontalDivider(color = Color(0xFFE5E7EB))
                    }
                }
            }
        }
    }
}

// ── Barra de búsqueda ─────────────────────────────────────────────────────────

@Composable
private fun MonitoringSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClear: () -> Unit,
    borderGray: Color
) {
    val hintGray = Color(0xFF9CA3AF)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .border(1.dp, borderGray, RoundedCornerShape(10.dp))
            .background(Color(0xFFF9FAFB), RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Buscar",
            tint = hintGray,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Box(modifier = Modifier.weight(1f)) {
            if (query.isEmpty()) {
                Text(
                    text = "Buscar por código, cliente o placa…",
                    fontSize = 13.sp,
                    color = hintGray
                )
            }
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                singleLine = true,
                textStyle = TextStyle(fontSize = 13.sp, color = Color(0xFF1F2937)),
                cursorBrush = SolidColor(Color(0xFF003B73)),
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (query.isNotEmpty()) {
            IconButton(onClick = onClear, modifier = Modifier.size(20.dp)) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "Limpiar búsqueda",
                    tint = hintGray,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

// ── Estado vacío ──────────────────────────────────────────────────────────────

@Composable
private fun MonitoringEmptyState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 56.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = null,
            tint = Color(0xFFD1D5DB),
            modifier = Modifier.size(56.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Sin resultados",
            color = Color(0xFF6B7280),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "No hay servicios con los filtros aplicados.",
            color = Color(0xFF9CA3AF),
            fontSize = 13.sp
        )
    }
}
