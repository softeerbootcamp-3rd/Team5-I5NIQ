package com.hyundai.myexperience.di

import com.hyundai.myexperience.data.ReservationRepository
import com.hyundai.myexperience.data.remote.ReservationQueueDataSource
import com.hyundai.myexperience.data.remote.ReservationRemoteDataSource
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.client.ReservationClient
import com.hyundai.myexperience.data.remote.service.ReservationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReservationModule {
    @Singleton
    @Provides
    fun provideReservationClient(): ReservationClient {
        return ReservationClient()
    }

    @Singleton
    @Provides
    fun provideReservationService(): ReservationService {
        val connection = ServerConnection.getInstance()

        return connection.create(ReservationService::class.java)
    }

    @Singleton
    @Provides
    fun provideReservationRemoteDataSource(service: ReservationService): ReservationRemoteDataSource {
        return ReservationRemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideReservationSocketDataSource(client: ReservationClient): ReservationQueueDataSource {
        return ReservationQueueDataSource(client)
    }

    @Singleton
    @Provides
    fun provideReservationRepository(
        remoteDataSource: ReservationRemoteDataSource,
        socketDataSource: ReservationQueueDataSource
    ): ReservationRepository {
        return ReservationRepository(remoteDataSource, socketDataSource)
    }
}