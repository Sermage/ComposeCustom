package com.example.composecustom.ui.stepper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecustom.R
import com.example.composecustom.ui.theme.Azalea
import com.example.composecustom.ui.theme.BurntSienna
import com.example.composecustom.ui.theme.ComposeCustomTheme

@Composable
fun CustomStepper(
    modifier: Modifier = Modifier
) {
    var count by remember { mutableStateOf(0) }

    Row(
        modifier = modifier
            .background(color = Color.White, shape = RoundedCornerShape(99.dp))
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            onClick = { count = count.minus(1) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                tint = Azalea,
                contentDescription = "arrow left"
            )
        }

        Text(
            text = "$count",
            style = MaterialTheme.typography.h1,
            color = BurntSienna,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(vertical = 4.dp)
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            onClick = { count = count.plus(1) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "arrow right",
                tint = Azalea,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomStepperPreview() {
    ComposeCustomTheme() {
        Box(
            modifier = Modifier
                .background(color = BurntSienna)
                .fillMaxSize()
        ) {
            CustomStepper(modifier = Modifier.align(Center))
        }

    }
}