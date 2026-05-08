package com.il.apptransporte.ui.screens.access

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.il.apptransporte.R

@Composable
fun AccessSelectionScreen(
    onTransportistAccess: () -> Unit,
    onCoordinatorAccess: () -> Unit
) {
    val primaryBlue = Color(0xFF003B73)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp, vertical = 34.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_interlogistics),
            contentDescription = "Logo InterLogistics",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(72.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Bienvenido",
            color = primaryBlue,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Selecciona tu tipo de acceso",
            fontSize = 18.sp,
            color = Color(0xFF111827)
        )

        Spacer(modifier = Modifier.height(32.dp))

        AccessOptionCard(
            icon = Icons.Outlined.LocalShipping,
            title = "Acceso transportista",
            onClick = onTransportistAccess
        )

        Spacer(modifier = Modifier.height(18.dp))

        AccessOptionCard(
            icon = Icons.Outlined.Business,
            title = "Acceso interno",
            onClick = onCoordinatorAccess
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Serás dirigido al inicio de sesión correspondiente.",
            color = Color(0xFF6B7280),
            fontSize = 15.sp
        )
    }
}

@Composable
private fun AccessOptionCard(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    val primaryBlue = Color(0xFF003B73)
    val orange = Color(0xFFFF4B00)

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = primaryBlue,
                modifier = Modifier.size(58.dp)
            )

            Spacer(modifier = Modifier.width(22.dp))

            Text(
                text = title,
                color = primaryBlue,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = "Ingresar",
                tint = orange,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}