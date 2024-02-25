package com.hyundai.myexperience.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.ScheduleRepository
import com.hyundai.myexperience.data.entity.schedule.ScheduleDetailsItem
import com.hyundai.myexperience.data.entity.schedule.SchedulesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val repository: ScheduleRepository): ViewModel() {
    private var _schedules = MutableLiveData<List<SchedulesItem>>(listOf())
    val schedules: LiveData<List<SchedulesItem>> = _schedules

    private var _scheduleDetails = MutableLiveData<List<ScheduleDetailsItem>>(listOf())
    val scheduleDetails: LiveData<List<ScheduleDetailsItem>> = _scheduleDetails

    private var _selectedProgram = MutableLiveData("")
    val selectedProgram: LiveData<String> = _selectedProgram

    private var _selectedIdx = MutableLiveData(-1)
    val selectedIdx: LiveData<Int> = _selectedIdx

    fun requestSchedules(program: String) {
        viewModelScope.launch {
            _schedules.value = repository.requestSchedules(program)
        }
    }

    fun requestScheduleDetail(selectedDate: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _scheduleDetails.value =
                repository.requestScheduleDetail(selectedProgram.value!!, selectedDate)
            onSuccess()
        }
    }

    fun setSelectedProgram(program: String) {
        _selectedProgram.value = program
    }

    fun setSelectedIdx(idx: Int) {
        _selectedIdx.value = idx
    }
}