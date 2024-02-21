package com.hyundai.myexperience.ui.reservation_entrance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationEntranceViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _isSignedIn = MutableLiveData(false)
    val isSignedIn: LiveData<Boolean> = _isSignedIn

    fun checkSignedIn() {
        viewModelScope.launch {
            _isSignedIn.value = repository.getIsSigned()
        }
    }
}