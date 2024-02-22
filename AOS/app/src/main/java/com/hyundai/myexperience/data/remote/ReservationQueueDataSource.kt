package com.hyundai.myexperience.data.remote

import com.hyundai.myexperience.data.remote.client.ReservationClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReservationQueueDataSource @Inject constructor(private val client: ReservationClient) {
    suspend fun connect() {
        client.connect()
    }

    fun receiveData(): Flow<String> {
        return client.receiveData()
    }
}