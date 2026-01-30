package com.example.mat_base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mat_base.ui.screens.HomeScreen
import com.example.mat_base.ui.theme.MATBASETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MATBASETheme {
                HomeScreen()
            }
        }
    }
}
