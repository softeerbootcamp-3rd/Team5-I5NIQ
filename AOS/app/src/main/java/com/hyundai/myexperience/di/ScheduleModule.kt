package com.hyundai.myexperience.di

import com.hyundai.myexperience.data.repository.ScheduleRepository
import com.hyundai.myexperience.data.remote.data_source.ScheduleDataSource
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.service.ScheduleService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ScheduleModule {
    @Singleton
    @Provides
    fun provideScheduleService(): ScheduleService {
        val connection = ServerConnection.getInstance()

        return connection.create(ScheduleService::class.java)
    }

    @Singleton
    @Provides
    fun provideScheduleDataSource(service: ScheduleService): ScheduleDataSource {
        return ScheduleDataSource(service)
    }

    @Singleton
    @Provides
    fun provideScheduleRepository(remoteDataSource: ScheduleDataSource): ScheduleRepository {
        return ScheduleRepository(remoteDataSource)
    }
}