package com.hyundai.myexperience.di

import com.hyundai.myexperience.data.NoticeDetailRepository
import com.hyundai.myexperience.data.NoticeListRepository
import com.hyundai.myexperience.data.UserRepository
import com.hyundai.myexperience.data.remote.NoticeDetailRemoteDataSource
import com.hyundai.myexperience.data.remote.NoticeListRemoteDataSource
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.UserRemoteDataSource
import com.hyundai.myexperience.data.remote.service.NoticeDetailService
import com.hyundai.myexperience.data.remote.service.NoticeListService
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
    fun provideUserRepository(remoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepository(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideNoticeListService(): NoticeListService {
        val connection = ServerConnection.getInstance()

        return connection.create(NoticeListService::class.java)
    }

    @Singleton
    @Provides
    fun provideNoticeListRemoteDataSource(service: NoticeListService): NoticeListRemoteDataSource {
        return NoticeListRemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideNoticeListRepository(remoteDataSource: NoticeListRemoteDataSource): NoticeListRepository {
        return NoticeListRepository(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideNoticeDetailService(): NoticeDetailService {
        val connection = ServerConnection.getInstance()

        return connection.create(NoticeDetailService::class.java)
    }

    @Singleton
    @Provides
    fun provideNoticeDetailRemoteDataSource(service: NoticeDetailService): NoticeDetailRemoteDataSource {
        return NoticeDetailRemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideNoticeDetailRepository(remoteDataSource: NoticeDetailRemoteDataSource): NoticeDetailRepository {
        return NoticeDetailRepository(remoteDataSource)
    }
}