package com.il.apptransporte.navigation

sealed class Screen {
    object AccessSelection : Screen()

    object TransportistLogin : Screen()
    object CoordinatorLogin : Screen()

    object DailyTransportOrders : Screen()
    object TransportOrderDetail : Screen()
    object Evidence : Screen()
    object JourneySummary : Screen()
    object TransportistNotifications : Screen()
    object TransportistProfile : Screen()

    object TransportOrderMonitoring : Screen()
    object CoordinatorNotifications : Screen()
    object CoordinatorProfile : Screen()
}