package com.il.apptransporte.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.il.apptransporte.data.model.TransportOrderStatus

@Composable
fun StatusChip(
    status: TransportOrderStatus,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (status) {
        TransportOrderStatus.ASSIGNED -> Color(0xFFDDF4C6)
        TransportOrderStatus.ON_WAY_TO_WAREHOUSE -> Color(0xFFFFF4D6)
        TransportOrderStatus.MERCHANDISE_PICKED_UP -> Color(0xFFE2F1FF)
        TransportOrderStatus.ON_ROUTE_TO_CLIENT -> Color(0xFFE2F1FF)
        TransportOrderStatus.DELIVERING -> Color(0xFFFFF4D6)
        TransportOrderStatus.COMPLETED -> Color(0xFFDDF5E5)
    }

    val textColor = when (status) {
        TransportOrderStatus.ASSIGNED -> Color(0xFF33691E)
        TransportOrderStatus.ON_WAY_TO_WAREHOUSE -> Color(0xFF9A5B00)
        TransportOrderStatus.MERCHANDISE_PICKED_UP -> Color(0xFF003B73)
        TransportOrderStatus.ON_ROUTE_TO_CLIENT -> Color(0xFF003B73)
        TransportOrderStatus.DELIVERING -> Color(0xFF9A5B00)
        TransportOrderStatus.COMPLETED -> Color(0xFF1B7F3A)
    }

    Text(
        text = status.label,
        color = textColor,
        fontSize = 11.sp,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 9.dp, vertical = 4.dp)
    )
}