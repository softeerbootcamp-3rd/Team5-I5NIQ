package com.hyundai.myexperience.data.remote

import com.hyundai.myexperience.data.remote.client.ReservationClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReservationSocketDataSource @Inject constructor(private val client: ReservationClient) {
    suspend fun connect() {
        client.connect()
    }

    fun receive(): Flow<String> {
        return client.receiveMessages()
    }
}