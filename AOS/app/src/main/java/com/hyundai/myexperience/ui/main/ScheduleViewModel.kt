package com.hyundai.myexperience.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.ScheduleListRepository
import com.hyundai.myexperience.data.entity.ScheduleDetailsItem
import com.hyundai.myexperience.data.entity.SchedulesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val repository: ScheduleListRepository): ViewModel() {
    private var _schedules = MutableLiveData<List<SchedulesItem>>(mutableListOf())
    val schedules: LiveData<List<SchedulesItem>> = _schedules

    private var _scheduleDetails = MutableLiveData<List<ScheduleDetailsItem>>(mutableListOf())
    val scheduleDetails: LiveData<List<ScheduleDetailsItem>> = _scheduleDetails

    fun schedulePleasureRequest() {
        viewModelScope.launch {
            val newSchedules = repository.responsePleasure()
            _schedules.value = newSchedules!!
        }
    }

    fun scheduleExperienceRequest() {
        viewModelScope.launch {
            val newSchedules = repository.responseExperience()
            _schedules.value = newSchedules!!
        }
    }

    fun scheduleDetailRequest() {
        val list = mutableListOf(
            ScheduleDetailsItem("level 1", listOf("현대"))
        )
        _scheduleDetails.value = list
    }


}