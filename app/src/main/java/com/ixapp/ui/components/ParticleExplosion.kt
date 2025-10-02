package com.ixapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawCircle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

data class Particle(var pos: Offset, val vel: Offset, val color: Color, var alpha: Float)

@Composable
fun ParticleExplosion(modifier: Modifier = Modifier, color: Color = Color(0xFF34C759), onComplete: ()->Unit) {
    val particles = remember { mutableStateListOf<Particle>() }

    LaunchedEffect(Unit) {
        repeat(36) {
            val angle = Random.nextFloat() * 2f * Math.PI.toFloat()
            val speed = Random.nextFloat() * 8f + 4f
            val vx = cos(angle) * speed
            val vy = sin(angle) * speed - 2f
            particles.add(Particle(Offset.Zero, Offset(vx, vy), color, 1f))
        }
        val duration = 600
        val start = System.currentTimeMillis()
        while (System.currentTimeMillis() - start < duration) {
            particles.forEach { p ->
                val gravity = 0.6f
                val nx = p.pos.x + p.vel.x
                val ny = p.pos.y + p.vel.y + gravity
                p.pos = Offset(nx, ny)
                p.alpha = ((duration - (System.currentTimeMillis() - start)).toFloat() / duration).coerceIn(0f,1f)
            }
            delay(16)
        }
        onComplete()
    }

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            particles.forEach { p ->
                drawCircle(p.color.copy(alpha = p.alpha), radius = 4.dp.toPx(), center = p.pos)
            }
        }
    }
}
