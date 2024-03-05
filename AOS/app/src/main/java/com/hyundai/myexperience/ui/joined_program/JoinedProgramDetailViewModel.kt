package com.hyundai.myexperience.ui.joined_program

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.entity.user.JoinedProgramDetail
import com.hyundai.myexperience.data.repository.UserRepository
import com.hyundai.myexperience.utils.formatMyPageDate
import com.hyundai.myexperience.utils.getCompanyName
import com.hyundai.myexperience.utils.getLevel
import com.hyundai.myexperience.utils.getProgramName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinedProgramDetailViewModel @Inject constructor(private val repository: UserRepository) :
    ViewModel() {
    private var _reservationDetail = MutableLiveData(JoinedProgramDetail("", 0, 0, "", "", "", ""))
    var reservationDetail: LiveData<JoinedProgramDetail?> = _reservationDetail

    private var _reservationId = MutableLiveData<Long>(0)
    val reservationId: LiveData<Long> = _reservationId

    private var _programName = MutableLiveData<String>()
    val programName: LiveData<String> = _programName

    private var _carName = MutableLiveData<String>()
    val carName: LiveData<String> = _carName

    private var _participants = MutableLiveData<String>()
    val participants: LiveData<String> = _participants

    private var _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    fun joinedProgramRequest() {
        viewModelScope.launch {
            val response = repository.requestJoinedProgramDetail(reservationId.value!!)
            _reservationDetail.value = response
            setProgramName()
            setCarName()
            setParticipants()
            setStartDate()
        }
    }

    fun setReservationId(id: Long) {
        _reservationId.value = id
    }

    private fun setProgramName() {
        val category = getCompanyName(_reservationDetail.value?.programCategory!!) + " "
        val name = getProgramName(_reservationDetail.value?.programName!!) + "\n"
        val level = getLevel(_reservationDetail.value?.programLevel!!)
        _programName.value = category + name + level
    }

    private fun setCarName() {
        _carName.value = _reservationDetail.value?.carName
    }

    private fun setParticipants() {
        _participants.value = _reservationDetail.value?.participants.toString() + "명"
    }

    private fun setStartDate() {
        _startDate.value = _reservationDetail.value?.startDateTime?.formatMyPageDate()
    }
}