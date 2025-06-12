package com.example.mypizza.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypizza.R

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    onClickResetPizzas: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back_24),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onClickResetPizzas)
        )
        Text(
            text = "Pizza",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            painter = painterResource(R.drawable.heart),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
)
@Composable
private fun Preview() {
    AppBar()
}