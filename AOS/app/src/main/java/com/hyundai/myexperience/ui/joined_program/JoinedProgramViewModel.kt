package com.hyundai.myexperience.ui.joined_program

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.entity.user.JoinedProgramItem
import com.hyundai.myexperience.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinedProgramViewModel @Inject constructor(private val repository: UserRepository) :
    ViewModel() {
    private var _joinedPrograms = MutableLiveData<List<JoinedProgramItem>?>(mutableListOf())
    val joinedPrograms: MutableLiveData<List<JoinedProgramItem>?> = _joinedPrograms

    private var _isJoinedProgramsEmpty = MutableLiveData<Boolean>(true)
    val isJoinedProgramsEmpty: LiveData<Boolean> = _isJoinedProgramsEmpty

    fun joinedProgramRequest(status: String) {
        viewModelScope.launch {
            val newJoinedPrograms = repository.requestJoinedPrograms(status)
            if (newJoinedPrograms.isNullOrEmpty()) {
                _isJoinedProgramsEmpty.value = true
                _joinedPrograms.value = emptyList()
            } else {
                _isJoinedProgramsEmpty.value = false
                _joinedPrograms.value = newJoinedPrograms
            }
        }
    }
}