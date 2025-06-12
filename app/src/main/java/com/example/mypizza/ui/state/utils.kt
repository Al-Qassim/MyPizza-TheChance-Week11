package com.example.mypizza.ui.state

import com.example.mypizza.R
import kotlin.random.Random

fun getDefaultPizzas(): List<PizzaState> {
    return listOf(
        PizzaState(
            bread = PizzaBread.Bread1,
            size = PizzaSize.M,
            toppingsWithOffsets = listOf()
        ),
        PizzaState(
            bread = PizzaBread.Bread2,
            size = PizzaSize.M,
            toppingsWithOffsets = listOf()
        ),
        PizzaState(
            bread = PizzaBread.Bread3,
            size = PizzaSize.M,
            toppingsWithOffsets = listOf()
        ),
        PizzaState(
            bread = PizzaBread.Bread4,
            size = PizzaSize.M,
            toppingsWithOffsets = listOf()
        ),
        PizzaState(
            bread = PizzaBread.Bread5,
            size = PizzaSize.M,
            toppingsWithOffsets = listOf()
        ),
    )
}

fun getPaintersIdsForTopping(pizzaTopping: PizzaTopping): List<Int> {
    return when (pizzaTopping) {
        PizzaTopping.Basil ->
            listOf(
                R.drawable.basil_1,
                R.drawable.basil_2,
                R.drawable.basil_3,
                R.drawable.basil_4,
                R.drawable.basil_5,
                R.drawable.basil_6,
                R.drawable.basil_7,
                R.drawable.basil_8,
                R.drawable.basil_9,
                R.drawable.basil_10,
                R.drawable.basil_1,
                R.drawable.basil_2,
                R.drawable.basil_3,
                R.drawable.basil_4,
                R.drawable.basil_5,
                R.drawable.basil_6,
                R.drawable.basil_7,
                R.drawable.basil_8,
                R.drawable.basil_9,
                R.drawable.basil_10,
            )

        PizzaTopping.Onion -> listOf(
            R.drawable.onion_1,
            R.drawable.onion_2,
            R.drawable.onion_3,
            R.drawable.onion_4,
            R.drawable.onion_5,
            R.drawable.onion_6,
            R.drawable.onion_7,
            R.drawable.onion_8,
            R.drawable.onion_9,
            R.drawable.onion_10,
            R.drawable.onion_1,
            R.drawable.onion_2,
            R.drawable.onion_3,
            R.drawable.onion_4,
            R.drawable.onion_5,
            R.drawable.onion_6,
            R.drawable.onion_7,
            R.drawable.onion_8,
            R.drawable.onion_9,
            R.drawable.onion_10,
        )

        PizzaTopping.Broccoli -> listOf(
            R.drawable.broccoli_1,
            R.drawable.broccoli_2,
            R.drawable.broccoli_3,
            R.drawable.broccoli_4,
            R.drawable.broccoli_5,
            R.drawable.broccoli_6,
            R.drawable.broccoli_7,
            R.drawable.broccoli_8,
            R.drawable.broccoli_9,
            R.drawable.broccoli_10,
            R.drawable.broccoli_1,
            R.drawable.broccoli_2,
            R.drawable.broccoli_3,
            R.drawable.broccoli_4,
            R.drawable.broccoli_5,
            R.drawable.broccoli_6,
            R.drawable.broccoli_7,
            R.drawable.broccoli_8,
            R.drawable.broccoli_9,
            R.drawable.broccoli_10,
        )

        PizzaTopping.Mushroom -> listOf(
            R.drawable.mushroom_1,
            R.drawable.mushroom_2,
            R.drawable.mushroom_3,
            R.drawable.mushroom_4,
            R.drawable.mushroom_5,
            R.drawable.mushroom_6,
            R.drawable.mushroom_7,
            R.drawable.mushroom_8,
            R.drawable.mushroom_9,
            R.drawable.mushroom_10,
            R.drawable.mushroom_1,
            R.drawable.mushroom_2,
            R.drawable.mushroom_3,
            R.drawable.mushroom_4,
            R.drawable.mushroom_5,
            R.drawable.mushroom_6,
            R.drawable.mushroom_7,
            R.drawable.mushroom_8,
            R.drawable.mushroom_9,
            R.drawable.mushroom_10,
        )

        PizzaTopping.Sausage -> listOf(
            R.drawable.sausage_1,
            R.drawable.sausage_2,
            R.drawable.sausage_3,
            R.drawable.sausage_4,
            R.drawable.sausage_5,
            R.drawable.sausage_6,
            R.drawable.sausage_7,
            R.drawable.sausage_8,
            R.drawable.sausage_9,
            R.drawable.sausage_10,
            R.drawable.sausage_1,
            R.drawable.sausage_2,
            R.drawable.sausage_3,
            R.drawable.sausage_4,
            R.drawable.sausage_5,
            R.drawable.sausage_6,
            R.drawable.sausage_7,
            R.drawable.sausage_8,
            R.drawable.sausage_9,
            R.drawable.sausage_10,
        )
    }
}

fun getRandomToppingOffsets(radius: Int = 85, size: Int = 20): List<Pair<Int, Int>> {
    val radiusSquared = radius * radius
    val points = mutableSetOf<Pair<Int, Int>>()

    while (points.size < size) {
        val x = Random.nextInt(-radius, radius + 1)
        val y = Random.nextInt(-radius, radius + 1)
        if (x * x + y * y <= radiusSquared) {
            points.add(Pair(x, y))
        }
    }

    return points.toList()
}

fun getDefaultToppingPaintersId(pizzaTopping: PizzaTopping): Int {
    return when (pizzaTopping) {
        PizzaTopping.Basil -> R.drawable.basil_3
        PizzaTopping.Onion -> R.drawable.onion_3
        PizzaTopping.Broccoli -> R.drawable.broccoli_3
        PizzaTopping.Mushroom -> R.drawable.mushroom_3
        PizzaTopping.Sausage -> R.drawable.sausage_3
    }
}

fun isToppingUsedOnPizza(pizza: PizzaState, topping: PizzaTopping): Boolean {
    return pizza.toppingsWithOffsets.any { toppingWithOffsets ->
        toppingWithOffsets.pizzaTopping == topping
    }
}