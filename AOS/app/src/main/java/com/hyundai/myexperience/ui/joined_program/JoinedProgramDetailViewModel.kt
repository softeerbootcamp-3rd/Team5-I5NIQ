package com.hyundai.myexperience.ui.joined_program

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.JoinedProgramRepository
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinedProgramDetailViewModel @Inject constructor(private val repository: JoinedProgramRepository): ViewModel() {
    private var _reservationDetail = MutableLiveData(JoinedProgramDetail("", 0, 0, "", "", "", ""))
    var reservationDetail: LiveData<JoinedProgramDetail?> = _reservationDetail

    private var _reservationId = MutableLiveData<Int>()
    val reservationId: LiveData<Int> = _reservationId

    fun joinedProgramRequest() {
        viewModelScope.launch {
            val response = repository.requestJoinedProgramDetail(reservationId.value!!)
            _reservationDetail.value = response!!
        }
    }

    fun setReservationId(id: Int){
        _reservationId.value = id
    }
}