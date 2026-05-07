package com.il.apptransporte.data.model

data class TransportOrder(
    val code: String,
    val warehouse: String,
    val warehouseAddress: String,
    val client: String,
    val clientAddress: String,
    val vehicleType: String,
    val plate: String,
    val documentCode: String,
    val scheduledTime: String,
    val status: TransportOrderStatus,
    val transportist: String = "Juan Pérez"
)

enum class TransportOrderStatus(val label: String) {
    ASSIGNED("Asignado"),
    ON_WAY_TO_WAREHOUSE("En camino al almacén"),
    MERCHANDISE_PICKED_UP("Mercadería retirada"),
    ON_ROUTE_TO_CLIENT("En ruta al cliente"),
    DELIVERING("Descargando / entregando"),
    COMPLETED("Entrega completada")
}