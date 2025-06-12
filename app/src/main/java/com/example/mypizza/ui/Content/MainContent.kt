package com.example.mypizza.ui.Content

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import androidx.core.view.WindowInsetsControllerCompat
import com.example.mypizza.ui.composable.AppBar
import com.example.mypizza.ui.composable.PizzaSection
import com.example.mypizza.ui.composable.SizeButtons
import com.example.mypizza.ui.composable.TappingSection
import com.example.mypizza.ui.state.PizzaSize
import com.example.mypizza.ui.state.PizzaState
import com.example.mypizza.ui.state.PizzaTopping
import com.example.mypizza.ui.state.getDefaultPizzas

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    pizzasState: List<PizzaState>,
    activePizzaIndex: Int = 0,
    onClickChangeSize: (PizzaSize) -> Unit = {},
    onSwipeSetActivePizzaIndexState: (Int) -> Unit = {},
    onClickToppingButton: (PizzaTopping) -> Unit = {},
    onClickResetPizzas: () -> Unit = {},
) {

    SetStatusBarIconColor(isDarkIcons = true)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            AppBar(onClickResetPizzas = onClickResetPizzas)
            PizzaSection(
                pizzasState = pizzasState,
                activePizzaIndex = activePizzaIndex,
                onSwipeSetActivePizzaIndexState = onSwipeSetActivePizzaIndexState,
            )
            SizeButtons(
                pizzaState = pizzasState[activePizzaIndex],
                onClickChangeSize = onClickChangeSize,
            )
            TappingSection(
                pizzaState = pizzasState[activePizzaIndex],
                onClickToppingButton = onClickToppingButton
            )
        }
    }
}

@SuppressLint("RestrictedApi")
@Composable
fun SetStatusBarIconColor(isDarkIcons: Boolean) {
    val view = LocalView.current
    val window = (view.context as? ComponentActivity)?.window ?: return
    val insetsController = WindowInsetsControllerCompat(window, view)
    insetsController.isAppearanceLightStatusBars = isDarkIcons
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 742
)

@Composable
private fun Preview() {
    MainContent(
        pizzasState = getDefaultPizzas()
    )
}