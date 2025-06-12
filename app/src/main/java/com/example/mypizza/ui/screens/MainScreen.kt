package com.example.mypizza.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.mypizza.ui.Content.MainContent
import com.example.mypizza.ui.viewModel.PizzaViewModel
import androidx.compose.runtime.getValue
import com.example.mypizza.ui.state.PizzaSize
import com.example.mypizza.ui.state.PizzaTopping

@Composable
fun MainScreen(
    viewModel: PizzaViewModel
) {
    val pizzaStates by viewModel.pizzasUiState.collectAsState()
    val activePizzaIndex by viewModel.activePizzaIndexState.collectAsState()

    val onClickChangeSize: (PizzaSize) -> Unit = (viewModel::onClickChangeSize)
    val onSwipeSetActivePizzaIndexState: (Int) -> Unit = (viewModel::onSwipeSetActivePizzaIndexState)
    val onClickToppingButton: (PizzaTopping) -> Unit = (viewModel::onClickToppingButton)
    val onClickResetPizzas: () -> Unit = (viewModel::onClickResetPizzas)

    MainContent(
        pizzasState = pizzaStates,
        activePizzaIndex = activePizzaIndex,
        onClickChangeSize = onClickChangeSize,
        onSwipeSetActivePizzaIndexState = onSwipeSetActivePizzaIndexState,
        onClickToppingButton = onClickToppingButton,
        onClickResetPizzas = onClickResetPizzas
    )
}
