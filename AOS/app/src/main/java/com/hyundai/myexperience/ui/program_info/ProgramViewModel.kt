package com.hyundai.myexperience.ui.program_info

import ProgramConfData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.ProgramRepository
import com.hyundai.myexperience.data.entity.program.ProgramMajorData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgramViewModel @Inject constructor(private val repository: ProgramRepository) :
    ViewModel() {
    private val _id = MutableLiveData(1)
    val id: LiveData<Int> = _id

    private val _majorData = MutableLiveData<ProgramMajorData?>()
    val majorData: LiveData<ProgramMajorData?> = _majorData

    private val _confData = MutableLiveData<ProgramConfData?>()
    val confData: LiveData<ProgramConfData?> = _confData

    fun requestProgramMajorData() {
        viewModelScope.launch {
            _majorData.value = repository.requestProgramMajorData(id.value!!)
        }
    }

    fun requestProgramConfData() {
        viewModelScope.launch {
            _confData.value = repository.requestProgramConfData(id.value!!)
        }
    }
}