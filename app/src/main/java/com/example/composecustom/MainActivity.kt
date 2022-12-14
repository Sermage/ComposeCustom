package com.example.composecustom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composecustom.ui.stepper.CustomStepper
import com.example.composecustom.ui.tab_row.CustomTabs
import com.example.composecustom.ui.theme.ComposeCustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCustomTheme(darkTheme = true) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.background)
                ) {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        val tabs = listOf("Direct Sale", "Rent", "Presale")
                        CustomTabs(tabs = tabs)

                        CustomStepper(
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(top = 100.dp)
                        )
                    }

                }
            }
        }
    }
}
