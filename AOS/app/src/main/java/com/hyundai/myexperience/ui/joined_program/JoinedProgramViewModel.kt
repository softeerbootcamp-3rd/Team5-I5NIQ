package com.hyundai.myexperience.ui.joined_program

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.JoinedProgramRepository
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinedProgramViewModel @Inject constructor(private val repository: JoinedProgramRepository): ViewModel() {
    private var _joinedPrograms = MutableLiveData<List<JoinedProgramItem>>(mutableListOf())
    val joinedPrograms: LiveData<List<JoinedProgramItem>> = _joinedPrograms

    fun joinedProgramRequest() {
        viewModelScope.launch {
            val newJoinedPrograms = repository.requestJoinedPrograms("")
            _joinedPrograms.value = newJoinedPrograms!!
        }
    }



}