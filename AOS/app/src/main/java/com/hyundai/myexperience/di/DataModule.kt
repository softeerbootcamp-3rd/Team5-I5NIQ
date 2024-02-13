package com.hyundai.myexperience.di

import com.hyundai.myexperience.data.UserRepository
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.UserRemoteDataSource
import com.hyundai.myexperience.data.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun provideService(): UserService {
        val connection = ServerConnection.getInstance()

        return connection.create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(service: UserService): UserRemoteDataSource {
        return UserRemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideUserRepository(remoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepository(remoteDataSource)
    }
}