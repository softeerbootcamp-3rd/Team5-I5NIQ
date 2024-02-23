package com.hyundai.myexperience.data.remote.client

import android.util.Log
import com.hyundai.myexperience.SOCKET_PORT
import com.hyundai.myexperience.SOCKET_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import java.net.SocketException

class ReservationClient {
    private lateinit var socket: Socket

    suspend fun connect() {
        withContext(Dispatchers.IO) {
            socket = Socket(SOCKET_URL, SOCKET_PORT)
        }
    }

    fun receiveData(): Flow<String> {
        return flow {
            try {
                BufferedReader(InputStreamReader(socket.getInputStream())).use { reader ->
                    while (true) {
                        val message = reader.readLine() ?: break
                        Log.d("check_cnt", message)
                        emit(message)
                    }
                }
            } catch (e: SocketException) {

            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun disconnect() {
        withContext(Dispatchers.IO) {
            socket.close()
        }
    }
}
