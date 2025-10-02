package com.ixapp.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun VoiceBubble(durationText: String, isMe: Boolean, onPlay: ()->Unit) {
    val scale = remember { Animatable(0f) }
    LaunchedEffect(durationText) {
        scale.animateTo(1f, animationSpec = spring(dampingRatio = 0.6f, stiffness = 350f))
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = if (isMe) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = if (isMe) Color(0xFF34C759) else Color(0xFFF0F0F0),
            shadowElevation = 2.dp,
            modifier = Modifier.scale(scale.value)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)) {
                Icon(Icons.Default.PlayArrow, contentDescription = "播放")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = durationText, color = if (isMe) Color.White else Color.Black, fontSize = 16.sp)
            }
        }
    }
}
