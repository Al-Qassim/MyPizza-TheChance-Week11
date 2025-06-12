package com.example.mypizza.ui.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypizza.ui.state.PizzaSize
import com.example.mypizza.ui.state.PizzaState
import com.example.mypizza.ui.state.getDefaultPizzas
import com.example.myweather.ui.extensionFunctions.dropShadow
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun SizeButtons(
    modifier: Modifier = Modifier,
    pizzaState: PizzaState,
    onClickChangeSize: (PizzaSize) -> Unit = {},
){
    val alignment = when (pizzaState.size) {
        PizzaSize.S -> Alignment.CenterStart
        PizzaSize.M -> Alignment.Center
        PizzaSize.L -> Alignment.CenterEnd
    }

    val animatedAlignment = animateAlignmentAsState(alignment)

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Box {
            ShadowedButtonCircle(modifier = modifier.align(animatedAlignment))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SizeButton(text = "S", onClick = { onClickChangeSize(PizzaSize.S) })
                SizeButton(text = "M", onClick = { onClickChangeSize(PizzaSize.M) })
                SizeButton(text = "L", onClick = { onClickChangeSize(PizzaSize.L) })
            }
        }
    }
}

@Composable
private fun animateAlignmentAsState(
    targetAlignment: Alignment,
    animationDuration: Int = 300
): Alignment {
    val targetFactor = when (targetAlignment) {
        Alignment.CenterStart -> -1f
        Alignment.Center -> 0f
        Alignment.CenterEnd -> 1f
        else -> 0f
    }

    val animatedFactor by animateFloatAsState(
        targetValue = targetFactor,
        animationSpec = tween(durationMillis = animationDuration),
        label = "alignmentAnimation"
    )

    return object : Alignment {
        override fun align(
            size: IntSize,
            space: IntSize,
            layoutDirection: LayoutDirection
        ): IntOffset {
            val centerX = (space.width - size.width) / 2
            val offsetX = ((space.width - size.width) / 2) * animatedFactor
            val x = (centerX + offsetX).toInt()
            val y = (space.height - size.height) / 2
            return IntOffset(x, y)
        }
    }
}

@Composable
fun ShadowedButtonCircle(
    modifier: Modifier = Modifier,
    ){
    Box(
        modifier = modifier
            .size(48.dp)
            .dropShadow(CircleShape, offsetX = 4.dp)
            .background(Color.White, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {}
}

@Composable
fun SizeButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
){
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black.copy(alpha = 0.60f)
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
)
@Composable
private fun Preview() {
    SizeButtons(pizzaState = getDefaultPizzas()[0])
}