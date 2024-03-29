package com.hyundai.myexperience.ui.reservation_entrance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.repository.ReservationRepository
import com.hyundai.myexperience.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationEntranceViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val reservationRepository: ReservationRepository
) : ViewModel() {
    private val _isSignedIn = MutableLiveData(false)
    val isSignedIn: LiveData<Boolean> = _isSignedIn

    private val _waitingCnt = MutableLiveData(0)
    val waitingCnt: LiveData<Int> = _waitingCnt

    private val _selectionType = MutableLiveData(0)
    val selectionType: LiveData<Int> = _selectionType

    private val _queueingFinished = MutableLiveData(false)
    val queueingFinished: LiveData<Boolean> = _queueingFinished

    fun checkSignedIn() {
        viewModelScope.launch {
            _isSignedIn.value = userRepository.getIsSigned()

            if (isSignedIn.value!!) {
                val cookie = userRepository.getCookie()
                userRepository.setCookieToConnection(cookie)

                if (userRepository.requestMypage() == null) {
                    userRepository.setIsSigned(false)
                }
            }
        }
    }

    fun startDataReceiving() {
        _queueingFinished.value = false

        viewModelScope.launch {
            reservationRepository.initConnection()
            reservationRepository.receiveData().collect {
                val cnt = it.toInt()
                if (cnt < 100000) {
                    _waitingCnt.postValue(cnt)
                } else {
                    if (!_queueingFinished.value!!) {
                        _queueingFinished.value = true
                    }
                    reservationRepository.closeConnection()
                }
            }
        }
    }

    fun setSelectionType(type: Int) {
        _selectionType.value = type
    }

    fun stopDataReceiving() {
        viewModelScope.launch {
            reservationRepository.closeConnection()
        }
    }
}