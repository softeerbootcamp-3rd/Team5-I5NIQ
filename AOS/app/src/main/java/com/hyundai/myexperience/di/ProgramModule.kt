package com.hyundai.myexperience.di

import com.hyundai.myexperience.data.ProgramRepository
import com.hyundai.myexperience.data.remote.ProgramRemoteDataSource
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.service.ProgramService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProgramModule {
    @Singleton
    @Provides
    fun provideProgramService(): ProgramService {
        val connection = ServerConnection.getInstance()

        return connection.create(ProgramService::class.java)
    }

    @Singleton
    @Provides
    fun provideProgramDataSource(service: ProgramService): ProgramRemoteDataSource {
        return ProgramRemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideProgramRepository(remoteDataSource: ProgramRemoteDataSource): ProgramRepository {
        return ProgramRepository(remoteDataSource)
    }
}