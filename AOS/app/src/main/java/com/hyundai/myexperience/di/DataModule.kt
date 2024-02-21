package com.hyundai.myexperience.di

import android.content.Context
import com.hyundai.myexperience.data.NoticeDetailRepository
import com.hyundai.myexperience.data.NoticeListRepository
import com.hyundai.myexperience.data.ReservationRepository
import com.hyundai.myexperience.data.ScheduleListRepository
import com.hyundai.myexperience.data.UserRepository
import com.hyundai.myexperience.data.local.UserLocalDataSource
import com.hyundai.myexperience.data.remote.NoticeDetailRemoteDataSource
import com.hyundai.myexperience.data.remote.NoticeListRemoteDataSource
import com.hyundai.myexperience.data.remote.ReservationRemoteDataSource
import com.hyundai.myexperience.data.remote.ScheduleListDataSource
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.UserRemoteDataSource
import com.hyundai.myexperience.data.remote.service.NoticeDetailService
import com.hyundai.myexperience.data.remote.service.NoticeListService
import com.hyundai.myexperience.data.remote.service.ReservationService
import com.hyundai.myexperience.data.remote.service.ScheduleListService
import com.hyundai.myexperience.data.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideReservationRepository(remoteDataSource: ReservationRemoteDataSource): ReservationRepository {
        return ReservationRepository(remoteDataSource)
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

    @Singleton
    @Provides
    fun provideScheduleListService(): ScheduleListService {
        val connection = ServerConnection.getInstance()

        return connection.create(ScheduleListService::class.java)
    }

    @Singleton
    @Provides
    fun provideScheduleListDataSource(service: ScheduleListService): ScheduleListDataSource {
        return ScheduleListDataSource(service)
    }

    @Singleton
    @Provides
    fun provideScheduleListRepository(remoteDataSource: ScheduleListDataSource): ScheduleListRepository {
        return ScheduleListRepository(remoteDataSource)
    }
}