package com.example.mypizza.ui.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypizza.R
import com.example.mypizza.ui.state.PizzaState
import com.example.mypizza.ui.state.PizzaTopping
import com.example.mypizza.ui.state.getDefaultPizzas
import com.example.mypizza.ui.state.getDefaultToppingPaintersId
import com.example.mypizza.ui.state.isToppingUsedOnPizza
import com.example.mypizza.ui.theme.ActiveTappingButtonColor
import com.example.mypizza.ui.theme.InactiveTappingButtonColor

@Composable
fun TappingSection(
    modifier: Modifier = Modifier,
    pizzaState: PizzaState,
    onClickToppingButton: (PizzaTopping) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "CUSTOMIZE YOUR PIZZA",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black.copy(alpha = 0.30f),
            modifier = Modifier.padding(start = 12.dp)
        )
        Spacer(Modifier.padding(top = 24.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            PizzaTopping.entries.forEach { topping ->
                TappingButton(
                    topping = topping,
                    pizzaState = pizzaState,
                    onClickToppingButton = onClickToppingButton,
                )
            }
        }
        Spacer(Modifier.padding(top = 42.dp))
        Spacer(Modifier.weight(1f))
        AddToCartButton(Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun TappingButton(
    modifier: Modifier = Modifier,
    topping: PizzaTopping,
    pizzaState: PizzaState,
    onClickToppingButton: (PizzaTopping) -> Unit
) {
    val color by animateColorAsState(
        if (isToppingUsedOnPizza(pizzaState, topping)) ActiveTappingButtonColor
        else InactiveTappingButtonColor
    )

    Box(
        modifier = modifier
            .size(60.dp)
            .background(
                color = color,
                shape = CircleShape
            )
            .clip(shape = CircleShape)
            .clickable(onClick = { onClickToppingButton(topping) }),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(getDefaultToppingPaintersId(topping)),
            contentDescription = null,
            modifier = modifier.size(38.dp)
        )
    }
}

@Composable
fun AddToCartButton(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .size(176.dp, 42.dp)
            .background(
                color = Color(0xFF372B28),
                shape = RoundedCornerShape(12.dp)
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.cart),
            contentDescription = null,
            tint = Color.White,
            modifier = modifier.size(24.dp)
        )
        Spacer(Modifier.padding(start = 8.dp))
        Text(
            text = "Add to cart",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
)
@Composable
private fun Preview() {
    TappingSection(pizzaState = getDefaultPizzas()[0])
}