package com.example.mypizza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mypizza.ui.screens.MainScreen
import com.example.mypizza.ui.viewModel.PizzaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = PizzaViewModel()

        setContent {
            MainScreen(viewModel = viewModel)
        }
    }
}
