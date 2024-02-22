package com.hyundai.myexperience.di

import android.content.Context
import com.hyundai.myexperience.data.NoticeRepository
import com.hyundai.myexperience.data.ProgramRepository
import com.hyundai.myexperience.data.ReservationRepository
import com.hyundai.myexperience.data.ScheduleDetailListRepository
import com.hyundai.myexperience.data.ScheduleListRepository
import com.hyundai.myexperience.data.UserRepository
import com.hyundai.myexperience.data.local.UserLocalDataSource
import com.hyundai.myexperience.data.remote.NoticeRemoteDataSource
import com.hyundai.myexperience.data.remote.ProgramRemoteDataSource
import com.hyundai.myexperience.data.remote.ReservationRemoteDataSource
import com.hyundai.myexperience.data.remote.ScheduleDetailListRemoteDataSource
import com.hyundai.myexperience.data.remote.ScheduleListDataSource
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.UserRemoteDataSource
import com.hyundai.myexperience.data.remote.service.NoticeService
import com.hyundai.myexperience.data.remote.service.ProgramService
import com.hyundai.myexperience.data.remote.service.ReservationService
import com.hyundai.myexperience.data.remote.service.ScheduleDetailListService
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

    @Singleton
    @Provides
    fun provideScheduleDetailListService(): ScheduleDetailListService {
        val connection = ServerConnection.getInstance()

        return connection.create(ScheduleDetailListService::class.java)
    }

    @Singleton
    @Provides
    fun provideScheduleDetailListDataSource(service: ScheduleDetailListService): ScheduleDetailListRemoteDataSource {
        return ScheduleDetailListRemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideScheduleDetailListRepository(remoteDataSource: ScheduleDetailListRemoteDataSource): ScheduleDetailListRepository {
        return ScheduleDetailListRepository(remoteDataSource)
    }
}