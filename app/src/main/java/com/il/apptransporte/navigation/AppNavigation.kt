package com.il.apptransporte.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.il.apptransporte.data.local.SampleDataSource
import com.il.apptransporte.data.model.TransportOrder
import com.il.apptransporte.data.model.TransportOrderStatus
import com.il.apptransporte.ui.screens.access.AccessSelectionScreen
import com.il.apptransporte.ui.screens.coordinator.CoordinatorNotificationsScreen
import com.il.apptransporte.ui.screens.coordinator.CoordinatorProfileScreen
import com.il.apptransporte.ui.screens.coordinator.TransportOrderMonitoringScreen
import com.il.apptransporte.ui.screens.login.CoordinatorLoginScreen
import com.il.apptransporte.ui.screens.login.TransportistLoginScreen
import com.il.apptransporte.ui.screens.transportist.DailyTransportOrdersScreen
import com.il.apptransporte.ui.screens.transportist.EvidenceScreen
import com.il.apptransporte.ui.screens.transportist.JourneySummaryScreen
import com.il.apptransporte.ui.screens.transportist.TransportOrderDetailScreen
import com.il.apptransporte.ui.screens.transportist.TransportistNotificationsScreen
import com.il.apptransporte.ui.screens.transportist.TransportistProfileScreen

@Composable
fun AppNavigation() {
    var currentScreen by remember {
        mutableStateOf<Screen>(Screen.AccessSelection)
    }

    var selectedTransportOrder by remember {
        mutableStateOf<TransportOrder?>(SampleDataSource.transportOrders.firstOrNull())
    }

    when (currentScreen) {
        Screen.AccessSelection -> AccessSelectionScreen(
            onTransportistAccess = {
                currentScreen = Screen.TransportistLogin
            },
            onCoordinatorAccess = {
                currentScreen = Screen.CoordinatorLogin
            }
        )

        Screen.TransportistLogin -> TransportistLoginScreen(
            onLogin = {
                currentScreen = Screen.DailyTransportOrders
            },
            onBack = {
                currentScreen = Screen.AccessSelection
            }
        )

        Screen.CoordinatorLogin -> CoordinatorLoginScreen(
            onLogin = {
                currentScreen = Screen.TransportOrderMonitoring
            },
            onBack = {
                currentScreen = Screen.AccessSelection
            }
        )

        Screen.DailyTransportOrders -> DailyTransportOrdersScreen(
            transportOrders = SampleDataSource.transportOrders,
            onUpdateStatus = { transportOrder ->
                selectedTransportOrder = transportOrder
                currentScreen = Screen.TransportOrderDetail
            },
            onGoToSummary = {
                currentScreen = Screen.JourneySummary
            },
            onGoToNotifications = {
                currentScreen = Screen.TransportistNotifications
            },
            onGoToProfile = {
                currentScreen = Screen.TransportistProfile
            }
        )

        Screen.TransportOrderDetail -> TransportOrderDetailScreen(
            transportOrder = selectedTransportOrder,
            onBack = {
                currentScreen = Screen.DailyTransportOrders
            },
            onSaveStatus = { selectedStatus ->
                if (selectedStatus == TransportOrderStatus.COMPLETED) {
                    currentScreen = Screen.Evidence
                } else {
                    currentScreen = Screen.DailyTransportOrders
                }
            }
        )

        Screen.Evidence -> EvidenceScreen(
            transportOrder = selectedTransportOrder,
            onBack = {
                currentScreen = Screen.DailyTransportOrders
            },
            onSaveEvidence = {
                currentScreen = Screen.DailyTransportOrders
            }
        )

        Screen.JourneySummary -> JourneySummaryScreen(
            transportOrders = SampleDataSource.transportOrders,
            onGoToServices = {
                currentScreen = Screen.DailyTransportOrders
            },
            onGoToNotifications = {
                currentScreen = Screen.TransportistNotifications
            },
            onGoToProfile = {
                currentScreen = Screen.TransportistProfile
            }
        )

        Screen.TransportistNotifications -> TransportistNotificationsScreen(
            notifications = SampleDataSource.transportistNotifications,
            onGoToServices = {
                currentScreen = Screen.DailyTransportOrders
            },
            onGoToSummary = {
                currentScreen = Screen.JourneySummary
            },
            onGoToProfile = {
                currentScreen = Screen.TransportistProfile
            }
        )

        Screen.TransportistProfile -> TransportistProfileScreen(
            user = SampleDataSource.transportistUser,
            onLogout = {
                currentScreen = Screen.AccessSelection
            },
            onGoToServices = {
                currentScreen = Screen.DailyTransportOrders
            },
            onGoToSummary = {
                currentScreen = Screen.JourneySummary
            },
            onGoToNotifications = {
                currentScreen = Screen.TransportistNotifications
            }
        )

        Screen.TransportOrderMonitoring -> TransportOrderMonitoringScreen(
            transportOrders = SampleDataSource.transportOrders,
            onGoToNotifications = {
                currentScreen = Screen.CoordinatorNotifications
            },
            onGoToProfile = {
                currentScreen = Screen.CoordinatorProfile
            }
        )

        Screen.CoordinatorNotifications -> CoordinatorNotificationsScreen(
            notifications = SampleDataSource.coordinatorNotifications,
            onGoToMonitoring = {
                currentScreen = Screen.TransportOrderMonitoring
            },
            onGoToProfile = {
                currentScreen = Screen.CoordinatorProfile
            }
        )

        Screen.CoordinatorProfile -> CoordinatorProfileScreen(
            user = SampleDataSource.coordinatorUser,
            onLogout = {
                currentScreen = Screen.AccessSelection
            },
            onGoToMonitoring = {
                currentScreen = Screen.TransportOrderMonitoring
            },
            onGoToNotifications = {
                currentScreen = Screen.CoordinatorNotifications
            }
        )
    }
}