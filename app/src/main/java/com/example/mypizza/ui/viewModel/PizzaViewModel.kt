package com.example.mypizza.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.mypizza.ui.state.PizzaSize
import com.example.mypizza.ui.state.PizzaTopping
import com.example.mypizza.ui.state.PizzaToppingWithOffsets
import com.example.mypizza.ui.state.getDefaultPizzas
import com.example.mypizza.ui.state.getRandomToppingOffsets
import com.example.mypizza.ui.state.isToppingUsedOnPizza
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PizzaViewModel : ViewModel() {
    private val _pizzasUiState = MutableStateFlow(getDefaultPizzas())
    val pizzasUiState = _pizzasUiState.asStateFlow()

    private val _activePizzaIndexState = MutableStateFlow(0)
    val activePizzaIndexState = _activePizzaIndexState.asStateFlow()

    fun onClickChangeSize(size: PizzaSize) {
        _pizzasUiState.update { currentList ->
            currentList.mapIndexed { index, pizza ->
                if (index == _activePizzaIndexState.value) pizza.copy(size = size) else pizza
            }
        }
    }

    fun onSwipeSetActivePizzaIndexState(newIndex: Int) {
        _activePizzaIndexState.update { newIndex }
    }

    fun onClickToppingButton(topping: PizzaTopping) {
        val currentPizza = _pizzasUiState.value[_activePizzaIndexState.value]
        val newPizza = if (isToppingUsedOnPizza(currentPizza, topping)) {
            currentPizza.copy(
                toppingsWithOffsets = currentPizza.toppingsWithOffsets
                    .filterNot { it.pizzaTopping == topping }
            )
        } else {
            currentPizza.copy(
                toppingsWithOffsets =
                    currentPizza.toppingsWithOffsets + PizzaToppingWithOffsets(
                        topping,
                        getRandomToppingOffsets()
                    )
            )
        }

        _pizzasUiState.update { currentList ->
            currentList.mapIndexed { index, pizza ->
                if (index == _activePizzaIndexState.value) newPizza else pizza
            }
        }
    }

    fun onClickResetPizzas() {
        _pizzasUiState.update { getDefaultPizzas() }
//        _activePizzaIndexState.update { 0 }
    }
}