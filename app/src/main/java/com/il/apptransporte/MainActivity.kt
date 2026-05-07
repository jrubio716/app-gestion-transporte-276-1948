package com.il.apptransporte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.il.apptransporte.navigation.AppNavigation
import com.il.apptransporte.ui.theme.AppGestionTransporteILTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppGestionTransporteILTheme {
                AppNavigation()
            }
        }
    }
}