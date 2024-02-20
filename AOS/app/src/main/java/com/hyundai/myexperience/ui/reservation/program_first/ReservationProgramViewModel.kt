package com.hyundai.myexperience.ui.reservation.program_first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationProgramViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    fun requestPrograms() {
        viewModelScope.launch {
        }
    }
}