package com.example.composecustom.ui.tab_row.utils

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.TabPosition
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp

fun Modifier.customTabIndicatorOffset(
    selectedTabPage: Int,
    tabPositions: List<TabPosition>,
    tabTextWidth: Dp,
    animationDelay: Int = 300
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = tabPositions[selectedTabPage]
    }
) {
    val transition = updateTransition(
        targetState = selectedTabPage,
        label = "Tab indicator"
    )
    val indicatorLeftPoint by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                tween(durationMillis = 500, delayMillis = animationDelay)
            } else {
                tween(durationMillis = 500)
            }
        },
        label = "Indicator left point"
    ) { page ->
        (tabPositions[page].left + tabPositions[page].right - tabTextWidth) / 2
    }
    val indicatorRightPoint by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                tween(durationMillis = 500)
            } else {
                tween(durationMillis = 500, delayMillis = animationDelay)
            }
        },
        label = "Indicator right point"
    ) { page ->
        (tabPositions[page].left + tabPositions[page].right - tabTextWidth) / 2 + tabTextWidth
    }

    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorLeftPoint)
        .width(indicatorRightPoint - indicatorLeftPoint)
}
