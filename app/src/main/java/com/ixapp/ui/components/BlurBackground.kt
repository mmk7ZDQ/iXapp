package com.ixapp.ui.components

import android.graphics.RenderEffect
import android.graphics.Shader
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun BlurBackground(modifier: Modifier = Modifier, content: @Composable ()->Unit) {
    Box(
        modifier = modifier
            .graphicsLayer {
                renderEffect = RenderEffect.createBlurEffect(18f, 18f, Shader.TileMode.CLAMP)
            }
            .background(Color(0x80FFFFFF))
            .fillMaxSize()
    ) {
        content()
    }
}
