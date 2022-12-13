package com.example.composecustom.ui.tab_row

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composecustom.ui.tab_row.utils.customTabIndicatorOffset
import com.example.composecustom.ui.theme.ComposeCustomTheme

@Composable
fun CustomTabs(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    animationDelay: Int = 300,
    paddingBetweenTextAndIndicatorDp: Int = 12,
    selectedTabColor: Color = Color.White,
    unselectedTabColor: Color = Color.Gray,
    indicatorColor: Color = Color.Red,
    indicatorHeightDp: Int = 4,
    indicatorCornerRadiusDp: Int = 10,
    tabTextStyle: TextStyle = MaterialTheme.typography.body1
) {
    var selectedTabPage by remember { mutableStateOf(0) }

    val tabTextWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    val density = LocalDensity.current

    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabPage,
        backgroundColor = Color.Transparent,
        divider = {},
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .customTabIndicatorOffset(
                        selectedTabPage,
                        tabPositions,
                        tabTextWidths[selectedTabPage],
                        animationDelay
                    )
                    .height(indicatorHeightDp.dp)
                    .background(
                        color = indicatorColor,
                        shape = RoundedCornerShape(indicatorCornerRadiusDp.dp)
                    )
            )
        }
    ) {
        tabs.forEachIndexed { index, tabItem ->

            val isSelected = selectedTabPage == index

            val textColor by animateColorAsState(
                targetValue = if (isSelected) {
                    selectedTabColor
                } else {
                    unselectedTabColor
                },
                tween(delayMillis = animationDelay)
            )
            Tab(
                selected = isSelected,
                onClick = { selectedTabPage = index }
            ) {
                Column(
                    Modifier
                        .padding(bottom = paddingBetweenTextAndIndicatorDp.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = tabItem,
                        style = tabTextStyle,
                        color = textColor,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        onTextLayout = { textLayoutResult ->
                            tabTextWidths[index] =
                                with(density) { textLayoutResult.size.width.toDp() }

                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TabsPreview() {
    val tabs = listOf("Sale", "Rent")
    ComposeCustomTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            CustomTabs(
                tabs = tabs, modifier = Modifier
                    .align(Center)
                    .padding(horizontal = 60.dp)
            )
        }
    }
}
