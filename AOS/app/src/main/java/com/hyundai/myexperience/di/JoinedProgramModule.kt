package com.hyundai.myexperience.di

import com.hyundai.myexperience.data.repository.JoinedProgramRepository
import com.hyundai.myexperience.data.remote.data_source.JoinedProgramRemoteDataSource
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.service.JoinedProgramService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class JoinedProgramModule {
    @Singleton
    @Provides
    fun provideJoinedProgramService(): JoinedProgramService {
        val connection = ServerConnection.getInstance()

        return connection.create(JoinedProgramService::class.java)
    }

    @Singleton
    @Provides
    fun provideJoinedProgramDataSource(service: JoinedProgramService): JoinedProgramRemoteDataSource {
        return JoinedProgramRemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideJoinedProgramRepository(remoteDataSource: JoinedProgramRemoteDataSource): JoinedProgramRepository {
        return JoinedProgramRepository(remoteDataSource)
    }
}