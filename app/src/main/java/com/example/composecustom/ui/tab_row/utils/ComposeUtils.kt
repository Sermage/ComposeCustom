package com.example.composecustom.ui.tab_row.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.TabPosition
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp
import com.example.composecustom.ui.tab_row.TabItem

fun Modifier.customTabIndicatorOffset(
    selectedTabPage: TabItem,
    tabPositions: List<TabPosition>,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = tabPositions[selectedTabPage.ordinal]
    }
) {

    val transition = updateTransition(
        targetState = selectedTabPage,
        label = "Tab indicator"
    )
    val indicatorLeft by transition.animateDp(
        transitionSpec = {
            if (TabItem.Sale isTransitioningTo TabItem.Rent) {
                spring(stiffness = Spring.StiffnessLow)
            } else {
                spring(stiffness = Spring.StiffnessHigh)
            }
        },
        label = "Indicator left"
    ) { page ->
        (tabPositions[page.ordinal].left + tabPositions[page.ordinal].right-tabWidth)/2
    }
    val indicatorRight by transition.animateDp(
        transitionSpec = {
            if (TabItem.Sale isTransitioningTo TabItem.Rent) {
                spring(stiffness = Spring.StiffnessHigh)
            } else {
                spring(stiffness = Spring.StiffnessLow)
            }
        },
        label = "Indicator right"
    ) { page ->
        (tabPositions[page.ordinal].left + tabPositions[page.ordinal].right-tabWidth)/2+tabWidth
    }

    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorLeft)
        .width(indicatorRight-indicatorLeft)

}