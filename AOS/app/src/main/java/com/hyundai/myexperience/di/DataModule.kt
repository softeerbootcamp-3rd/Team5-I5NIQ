package com.hyundai.myexperience.di

import android.content.Context
import com.hyundai.myexperience.data.JoinedProgramRepository
import com.hyundai.myexperience.data.NoticeRepository
import com.hyundai.myexperience.data.ProgramRepository
import com.hyundai.myexperience.data.ReservationRepository
import com.hyundai.myexperience.data.ScheduleRepository
import com.hyundai.myexperience.data.UserRepository
import com.hyundai.myexperience.data.local.UserLocalDataSource
import com.hyundai.myexperience.data.remote.JoinedProgramRemoteDataSource
import com.hyundai.myexperience.data.remote.NoticeRemoteDataSource
import com.hyundai.myexperience.data.remote.ProgramRemoteDataSource
import com.hyundai.myexperience.data.remote.ReservationRemoteDataSource
import com.hyundai.myexperience.data.remote.ReservationQueueDataSource
import com.hyundai.myexperience.data.remote.ScheduleDataSource
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.data.remote.UserRemoteDataSource
import com.hyundai.myexperience.data.remote.client.ReservationClient
import com.hyundai.myexperience.data.remote.service.JoinedProgramService
import com.hyundai.myexperience.data.remote.service.NoticeService
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