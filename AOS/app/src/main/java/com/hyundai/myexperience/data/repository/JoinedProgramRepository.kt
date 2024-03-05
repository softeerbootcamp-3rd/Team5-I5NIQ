package com.hyundai.myexperience.data.repository

import com.hyundai.myexperience.data.entity.my_page.JoinedProgramDetail
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramItem
import com.hyundai.myexperience.data.mapper.my_page.mapToJoinedDetail
import com.hyundai.myexperience.data.mapper.my_page.mapToJoinedProgramList
import com.hyundai.myexperience.data.remote.data_source.JoinedProgramRemoteDataSource
import javax.inject.Inject

class JoinedProgramRepository @Inject constructor(
    private val joinedProgramRemoteDataSource: JoinedProgramRemoteDataSource
) {
    suspend fun requestJoinedPrograms(status: String): List<JoinedProgramItem>? {
        return joinedProgramRemoteDataSource.requestJoinedPrograms(status)?.mapToJoinedProgramList()
    }

    suspend fun requestJoinedProgramDetail(id: Long): JoinedProgramDetail? {
        return joinedProgramRemoteDataSource.requestJoinedProgramDetail(id)?.mapToJoinedDetail()
    }
}