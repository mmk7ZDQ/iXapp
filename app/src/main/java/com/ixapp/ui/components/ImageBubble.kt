package com.ixapp.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material3.Surface

@Composable
fun ImageBubble(image: ImageBitmap, isMe: Boolean) {
    val scale = remember { Animatable(0f) }
    LaunchedEffect(image) {
        scale.animateTo(1f, animationSpec = spring(dampingRatio = 0.6f, stiffness = 350f))
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = if (isMe) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Surface(
            shape = RoundedCornerShape(18.dp),
            shadowElevation = 2.dp,
            modifier = Modifier.scale(scale.value)
        ) {
            Image(
                bitmap = image,
                contentDescription = "图片消息",
                modifier = Modifier.sizeIn(maxWidth = 220.dp, maxHeight = 220.dp)
            )
        }
    }
}
