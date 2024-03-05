package com.hyundai.myexperience.di

import android.content.Context
import com.hyundai.myexperience.data.UserRepository
import com.hyundai.myexperience.data.local.UserLocalDataSource
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.UserRemoteDataSource
import com.hyundai.myexperience.data.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserModule {
    @Singleton
    @Provides
    fun provideUserService(): UserService {
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
    fun provideUserLocalDataSource(@ApplicationContext context: Context): UserLocalDataSource {
        return UserLocalDataSource(context)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        localDataSource: UserLocalDataSource,
        remoteDataSource: UserRemoteDataSource
    ): UserRepository {
        return UserRepository(localDataSource, remoteDataSource)
    }
}