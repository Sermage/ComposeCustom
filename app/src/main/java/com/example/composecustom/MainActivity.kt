package com.example.composecustom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composecustom.ui.tab_row.CustomTabs
import com.example.composecustom.ui.tab_row.TabItem
import com.example.composecustom.ui.theme.ComposeCustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCustomTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.background)
                ) {
                    val tabs = listOf(TabItem.Sale, TabItem.Rent, TabItem.Presale)
                    CustomTabs(tabs = tabs, modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}
