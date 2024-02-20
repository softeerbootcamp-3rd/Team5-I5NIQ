package com.hyundai.myexperience.ui.reservation.program_first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.ReservationRepository
import com.hyundai.myexperience.data.entity.LevelsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationProgramViewModel @Inject constructor(private val repository: ReservationRepository) :
    ViewModel() {
    private var _levelsItems = MutableLiveData<List<LevelsItem>>(listOf())
    val levelsItems: LiveData<List<LevelsItem>> = _levelsItems

    fun requestPrograms() {
        viewModelScope.launch {
            _levelsItems.value = repository.requestExperiencePrograms()
        }
    }
}