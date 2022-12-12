package com.example.composecustom.ui.tab_row

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecustom.ui.tab_row.utils.customTabIndicatorOffset
import com.example.composecustom.ui.theme.ComposeCustomTheme

@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    tabs: List<TabItem>,
) {
    var selectedTabPage by remember { mutableStateOf(TabItem.Sale) }

    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    val density = LocalDensity.current

    TabRow(
        modifier = modifier
            .padding(horizontal = 80.dp),
        selectedTabIndex = selectedTabPage.ordinal,
        backgroundColor = Color.Transparent,
        divider = {},
        indicator = { tabPositions ->

            Box(
                modifier = Modifier
                    .customTabIndicatorOffset(selectedTabPage, tabPositions,tabWidths[selectedTabPage.ordinal])
                    .height(4.dp)
                    .background(color = MaterialTheme.colors.primary)
            )
        }
    ) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(
                selected = selectedTabPage.ordinal == index,
                onClick = { selectedTabPage = tabItem },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onBackground,
            ) {
                Column(
                    Modifier
                        .padding(bottom = 12.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = tabItem.title),
                        style = MaterialTheme.typography.body1,
                        letterSpacing = 2.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        onTextLayout = { textLayoutResult ->
                            tabWidths[index] =
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
    val tabs = listOf(TabItem.Sale, TabItem.Rent)
    ComposeCustomTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            Tabs(tabs = tabs, modifier = Modifier.align(Center))
        }
    }
}