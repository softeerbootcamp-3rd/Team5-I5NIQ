package com.hyundai.myexperience.ui.program_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.ProgramRepository
import com.hyundai.myexperience.data.entity.ProgramMajorData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgramViewModel @Inject constructor(private val repository: ProgramRepository) :
    ViewModel() {
    private val _majorData = MutableLiveData<ProgramMajorData>()
    val majorData: LiveData<ProgramMajorData> = _majorData

    fun requestProgramMajorData() {
        viewModelScope.launch {
            _majorData.value = repository.requestProgramMajorData(1)
        }
    }
}