package com.il.apptransporte.data.local

import com.il.apptransporte.data.model.AppNotification
import com.il.apptransporte.data.model.TransportOrder
import com.il.apptransporte.data.model.TransportOrderStatus
import com.il.apptransporte.data.model.User

object SampleDataSource {

    val transportistUser = User(
        fullName = "Juan Pérez",
        role = "Transportista",
        dni = "45678912",
        company = "Transportes Andinos S.A.C.",
        email = "juan.perez@transportesandinos.com",
        phone = "987 654 321",
        license = "A-IIIB"
    )

    val coordinatorUser = User(
        fullName = "Carla Mendoza",
        role = "Coordinadora de operaciones",
        email = "carla.mendoza@interlogistics.com.pe",
        phone = "987 654 321"
    )

    val transportOrders = listOf(
        TransportOrder(
            code = "SV-2026-001",
            warehouse = "Neptunia",
            warehouseAddress = "Av. Argentina 1234, Callao",
            client = "Comercial Andina S.A.C.",
            clientAddress = "Av. Argentina 1234, Lima 01",
            vehicleType = "Camión 3.5 ton",
            plate = "BTA-452",
            documentCode = "GU-2026-000123",
            scheduledTime = "11:30 AM",
            status = TransportOrderStatus.ASSIGNED
        ),
        TransportOrder(
            code = "SV-2026-002",
            warehouse = "Unimar",
            warehouseAddress = "Av. Contralmirante Mora 163, Callao",
            client = "Importaciones del Sur S.A.C.",
            clientAddress = "Jr. Los Industriales 456, Lima 03",
            vehicleType = "Camión 3.5 ton",
            plate = "BTA-452",
            documentCode = "GU-2026-000567",
            scheduledTime = "12:20 PM",
            status = TransportOrderStatus.MERCHANDISE_PICKED_UP
        ),
        TransportOrder(
            code = "SV-2026-003",
            warehouse = "APM Terminales Depósito Temporal",
            warehouseAddress = "Av. Néstor Gambetta, Callao",
            client = "Distribuidora Norte S.A.",
            clientAddress = "Av. Alfredo Mendiola 780, Los Olivos",
            vehicleType = "Camión 3.5 ton",
            plate = "BTA-452",
            documentCode = "GU-2026-000890",
            scheduledTime = "04:00 PM",
            status = TransportOrderStatus.ASSIGNED
        )
    )

    val transportistNotifications = listOf(
        AppNotification(
            title = "Nueva asignación",
            message = "Se le ha asignado un nuevo servicio.",
            time = "09:15"
        ),
        AppNotification(
            title = "Cambio de hora de retiro",
            message = "La hora de retiro del servicio ha cambiado.",
            time = "08:02"
        )
    )

    val coordinatorNotifications = listOf(
        AppNotification(
            title = "SV-2026-001",
            message = "El transportista actualizó el estado a Mercadería retirada.",
            time = "09:15"
        ),
        AppNotification(
            title = "SV-2026-002",
            message = "El transportista actualizó el estado a En ruta al cliente.",
            time = "10:02"
        ),
        AppNotification(
            title = "SV-2026-003",
            message = "Cambio de hora de retiro. Nueva hora programada: 16:00.",
            time = "11:20"
        )
    )
}