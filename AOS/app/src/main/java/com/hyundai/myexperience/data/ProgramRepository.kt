package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.program.Comment
import com.hyundai.myexperience.data.entity.program.ProgramConfData
import com.hyundai.myexperience.data.entity.program.ProgramMajorData
import com.hyundai.myexperience.data.entity.program.ProgramTrack
import com.hyundai.myexperience.data.mapper.program.mapToProgramConfData
import com.hyundai.myexperience.data.mapper.program.mapToProgramMajorData
import com.hyundai.myexperience.data.mapper.program.mapToProgramTrack
import com.hyundai.myexperience.data.remote.ProgramRemoteDataSource
import javax.inject.Inject

class ProgramRepository @Inject constructor(
    private val programRemoteDataSource: ProgramRemoteDataSource
) {
    suspend fun requestProgramMajorData(id: Int): ProgramMajorData? {
        return programRemoteDataSource.requestProgramMajorData(id)?.mapToProgramMajorData()
    }

    suspend fun requestProgramConfData(id: Int): ProgramConfData? {
        return programRemoteDataSource.requestProgramConfData(id)?.mapToProgramConfData()
    }

    suspend fun requestProgramTracks(id: Int): List<ProgramTrack>? {
        return programRemoteDataSource.requestProgramTrackData(id)?.circuits?.map { it.mapToProgramTrack() }
    }

    suspend fun requestProgramComments(id: Int): List<Comment>? {
        return programRemoteDataSource.requestProgramCommentData(id)?.comments?.map { it.mapToProgramTrack() }
    }
}