package com.ixapp.ui.pages

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.ixapp.ui.components.*
import com.ixapp.data.Message
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.InputStream

@Composable
fun ChatPage(messages: List<Message>, onSendText: (String)->Unit, onSendImage: (ByteArray)->Unit) {
    var text by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f), state = listState) {
            items(messages) { msg ->
                when (msg.type) {
                    "text" -> TextBubble(msg.content, msg.fromMe)
                    "image" -> {
                        val bmp = remember(msg.content) {
                            try {
                                val ist: InputStream? = context.contentResolver.openInputStream(Uri.parse(msg.content))
                                ist?.use { BitmapFactory.decodeStream(it) }?.asImageBitmap()
                            } catch (_: Exception) { null }
                        }
                        bmp?.let { ImageBubble(it, msg.fromMe) }
                    }
                    "voice" -> VoiceBubble("${msg.content}s", msg.fromMe) { /* play */ }
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    val ist = context.contentResolver.openInputStream(uri)
                    val bytes = ist?.readBytes()
                    bytes?.let { onSendImage(it) }
                }
            }

            IconButton(onClick = { launcher.launch("image/*") }) {
                Icon(imageVector = androidx.compose.material.icons.Icons.Default.Image, contentDescription = "图片")
            }

            TextField(value = text, onValueChange = { text = it }, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (text.isNotBlank()) { onSendText(text); text = "" }
            }) {
                Text(text = "发送", fontSize = 16.sp)
            }
        }
    }
}
