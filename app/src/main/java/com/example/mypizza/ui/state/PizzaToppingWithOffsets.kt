package com.example.mypizza.ui.state

data class PizzaToppingWithOffsets(
    val pizzaTopping: PizzaTopping,
    val offsets: List<Pair<Int, Int>>
)