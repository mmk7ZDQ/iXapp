package com.ixapp.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import kotlinx.coroutines.*
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class BluetoothManager {
    private val adapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val MY_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    private var socket: BluetoothSocket? = null
    private var input: InputStream? = null
    private var output: OutputStream? = null
    private var receiveJob: Job? = null

    var onMessageReceived: ((ByteArray) -> Unit)? = null

    fun startClient(device: BluetoothDevice, onConnected: ()->Unit, onError:(Throwable)->Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                socket = device.createRfcommSocketToServiceRecord(MY_UUID)
                adapter?.cancelDiscovery()
                socket?.connect()
                input = socket?.inputStream
                output = socket?.outputStream
                onConnected()
                startReceiving()
            } catch (e: Throwable) {
                onError(e)
            }
        }
    }

    fun send(data: ByteArray) {
        GlobalScope.launch(Dispatchers.IO) {
            try { output?.write(data) } catch (_: Exception) {}
        }
    }

    private fun startReceiving() {
        receiveJob = GlobalScope.launch(Dispatchers.IO) {
            val buffer = ByteArray(4096)
            while (isActive) {
                try {
                    val read = input?.read(buffer) ?: -1
                    if (read > 0) {
                        val copy = buffer.copyOf(read)
                        onMessageReceived?.invoke(copy)
                    }
                } catch (_: Exception) { break }
            }
        }
    }

    fun close() {
        try { socket?.close() } catch (_: Exception) {}
        receiveJob?.cancel()
    }
}
