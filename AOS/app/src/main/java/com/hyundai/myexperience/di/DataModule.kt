package com.hyundai.myexperience.di

import android.content.Context
import com.hyundai.myexperience.data.NoticeDetailRepository
import com.hyundai.myexperience.data.NoticeListRepository
import com.hyundai.myexperience.data.ProgramRepository
import com.hyundai.myexperience.data.ReservationRepository
import com.hyundai.myexperience.data.ScheduleRepository
import com.hyundai.myexperience.data.UserRepository
import com.hyundai.myexperience.data.local.UserLocalDataSource
import com.hyundai.myexperience.data.remote.NoticeDetailRemoteDataSource
import com.hyundai.myexperience.data.remote.NoticeListRemoteDataSource
import com.hyundai.myexperience.data.remote.ProgramRemoteDataSource
import com.hyundai.myexperience.data.remote.ReservationRemoteDataSource
import com.hyundai.myexperience.data.remote.ReservationQueueDataSource
import com.hyundai.myexperience.data.remote.ScheduleDataSource
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.UserRemoteDataSource
import com.hyundai.myexperience.data.remote.client.ReservationClient
import com.hyundai.myexperience.data.remote.service.NoticeDetailService
import com.hyundai.myexperience.data.remote.service.NoticeListService
import com.hyundai.myexperience.data.remote.service.ProgramService
import com.hyundai.myexperience.data.remote.service.ReservationService
import com.hyundai.myexperience.data.remote.service.ScheduleService
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