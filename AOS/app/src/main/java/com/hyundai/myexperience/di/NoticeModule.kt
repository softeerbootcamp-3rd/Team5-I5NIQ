package com.hyundai.myexperience.di

import com.hyundai.myexperience.data.repository.NoticeRepository
import com.hyundai.myexperience.data.remote.data_source.NoticeRemoteDataSource
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.service.NoticeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NoticeModule {
    @Singleton
    @Provides
    fun provideNoticeService(): NoticeService {
        val connection = ServerConnection.getInstance()

        return connection.create(NoticeService::class.java)
    }

    @Singleton
    @Provides
    fun provideNoticeRemoteDataSource(service: NoticeService): NoticeRemoteDataSource {
        return NoticeRemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideNoticeRepository(remoteDataSource: NoticeRemoteDataSource): NoticeRepository {
        return NoticeRepository(remoteDataSource)
    }
}