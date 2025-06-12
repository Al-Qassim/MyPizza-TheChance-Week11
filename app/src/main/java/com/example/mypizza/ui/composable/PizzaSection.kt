package com.example.mypizza.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypizza.R
import com.example.mypizza.ui.state.PizzaState
import com.example.mypizza.ui.state.PizzaTopping
import com.example.mypizza.ui.state.PizzaToppingWithOffsets
import com.example.mypizza.ui.state.getDefaultPizzas
import com.example.mypizza.ui.state.getPaintersIdsForTopping
import com.example.mypizza.ui.state.isToppingUsedOnPizza

@Composable
fun PizzaSection(
    modifier: Modifier = Modifier,
    pizzasState: List<PizzaState>,
    activePizzaIndex: Int = 0,
    onSwipeSetActivePizzaIndexState: (Int) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.plate),
                contentDescription = null,
                modifier = Modifier.height(247.dp)
            )
            PizzaWithTapping(
                pizzasState = pizzasState,
                onSwipeSetActivePizzaIndexState = onSwipeSetActivePizzaIndexState,
            )
        }
        Spacer(Modifier.padding(top = 24.dp))
        Text(
            text = "$17",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun PizzaWithTapping(
    modifier: Modifier = Modifier,
    pizzasState: List<PizzaState>,
    onSwipeSetActivePizzaIndexState: (Int) -> Unit = {},
) {
    val pagerState = rememberPagerState(pageCount = { pizzasState.size })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { currentPage ->
                onSwipeSetActivePizzaIndexState(currentPage)
        }
    }

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
    ) { page ->
        var animatedSize = animateFloatAsState(targetValue = pizzasState[page].size.scaleFactor)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .scale(animatedSize.value),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(pizzasState[page].bread.painterId),
                contentDescription = null,
                modifier = Modifier
                    .height(190.dp),
                contentScale = ContentScale.Fit
            )
            Toppings(
                modifier = Modifier.size(190.dp),
                pizza = pizzasState[page]
            )
        }
    }
}

@Composable
private fun Toppings(
    modifier: Modifier = Modifier,
    pizza: PizzaState
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ){
        PizzaTopping.entries.forEach { topping ->
            AnimatedVisibility (
                visible = isToppingUsedOnPizza(pizza, topping),
                enter = scaleIn(
                    animationSpec = tween(500),
                    initialScale = 2.5f
                ) + fadeIn(
                    animationSpec = tween(500)
                ),
                exit = fadeOut(
                    animationSpec = tween(0)
                ),
                label = topping.name
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    pizza.toppingsWithOffsets.firstOrNull {
                        it.pizzaTopping == topping
                    }?.also{ toppingWithOffsets ->
                        val painterIds = getPaintersIdsForTopping(toppingWithOffsets.pizzaTopping)
                        val offsets = toppingWithOffsets.offsets
                        painterIds.zip(offsets).forEach { (painterId, offset) ->
                            Image(
                                painter = painterResource(painterId),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(32.dp)
                                    .offset(x = offset.first.dp, y = offset.second.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
)
@Composable
private fun Preview() {
    PizzaSection(pizzasState = getDefaultPizzas())
}