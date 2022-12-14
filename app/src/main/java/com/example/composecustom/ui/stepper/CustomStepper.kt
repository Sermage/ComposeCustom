package com.example.composecustom.ui.stepper

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecustom.R
import com.example.composecustom.ui.theme.Azalea
import com.example.composecustom.ui.theme.BurntSienna
import com.example.composecustom.ui.theme.ComposeCustomTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomStepper(
    modifier: Modifier = Modifier
) {
    var count by remember { mutableStateOf(16) }

    var leftIconVisible by remember { mutableStateOf(true) }
    var rightIconVisible by remember { mutableStateOf(true) }
    var countVisibility by remember { mutableStateOf(true) }

    val scope = rememberCoroutineScope()

    SideEffect {
        scope.launch {
            if (leftIconVisible.not()) {
                delay(200)
                countVisibility = false
                delay(100)
                count = count.minus(1)
                leftIconVisible = true
                countVisibility = true
            }
            if (rightIconVisible.not()) {
                delay(200)
                countVisibility = false
                delay(100)
                count = count.plus(1)
                rightIconVisible = true
                countVisibility = true
            }
        }
    }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val sizeScale by animateFloatAsState(if (isPressed) 0.9f else 1f)

    val arrowColor by animateColorAsState(targetValue = if (isPressed) BurntSienna else Azalea)

    Card(
        modifier = modifier
            .wrapContentSize()
            .graphicsLayer {
                scaleX = sizeScale
                scaleY = sizeScale

            },
        backgroundColor = Color.White,
        shape = RoundedCornerShape(99.dp)
    ) {
        Row {
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterVertically),
                visible = leftIconVisible,
                enter = slideInHorizontally(tween(300)) + fadeIn(),
                exit = slideOutHorizontally(tween(200)) + fadeOut()
            ) {

                IconButton(
                    onClick = {
                        leftIconVisible = false
                    },
                    interactionSource = interactionSource
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        tint = arrowColor,
                        contentDescription = "arrow left"
                    )
                }
            }
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterVertically),
                visible = countVisibility,
                enter = slideInHorizontally(tween(300),
                    initialOffsetX = { if (leftIconVisible.not()) -it * 3 else it * 3 }) + fadeIn(
                    tween(durationMillis = 200)
                ),
                exit = slideOutHorizontally(
                    tween(200),
                    targetOffsetX = { if (leftIconVisible.not()) -it * 3 else it * 3 }) + fadeOut(
                    tween(durationMillis = 200)
                )
            ) {
                Text(
                    text = "$count",
                    style = MaterialTheme.typography.h1,
                    color = BurntSienna,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(vertical = 8.dp)
                )
            }

            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterVertically),
                visible = rightIconVisible,
                enter = slideInHorizontally(tween(300), initialOffsetX = { it / 2 }) + fadeIn(),
                exit = slideOutHorizontally(tween(200), targetOffsetX = { it / 2 }) + fadeOut()
            ) {

                IconButton(
                    onClick = {
                        rightIconVisible = false
                    },
                    interactionSource = interactionSource
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "arrow right",
                        tint = arrowColor,
                    )
                }
            }
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