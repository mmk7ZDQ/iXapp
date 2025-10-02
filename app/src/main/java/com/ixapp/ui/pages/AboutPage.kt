package com.ixapp.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.ixapp.ui.components.BlurBackground

@Composable
fun AboutPage() {
    BlurBackground {
        Column(modifier = Modifier.fillMaxSize().padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "iX", fontSize = 40.sp, fontFamily = FontFamily(Font(R.font.cursive_font)))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "版本 1.0.0", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "“自由与隐私，是我们数字世界里最后的堡垒。”", fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.cursive_font)))
        }
    }
}
