package com.ixapp.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsPage(onShowAbout: ()->Unit) {
    var taps by remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "版本 1.0.0", modifier = Modifier
            .clickable {
                taps += 1
                if (taps >= 5) { onShowAbout(); taps = 0 }
            }
            .padding(8.dp)
        )
    }
}
