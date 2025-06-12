package com.example.mypizza.ui.state

data class PizzaState (
    val bread: PizzaBread,
    val size: PizzaSize,
    val toppingsWithOffsets: List<PizzaToppingWithOffsets>
)