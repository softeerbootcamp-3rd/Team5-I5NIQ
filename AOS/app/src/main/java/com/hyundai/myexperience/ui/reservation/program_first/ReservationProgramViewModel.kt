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
    private var _experiencePrograms = MutableLiveData<List<LevelsItem>>(listOf())
    val experiencePrograms: LiveData<List<LevelsItem>> = _experiencePrograms

    private var _pleasurePrograms = MutableLiveData<List<LevelsItem>>(listOf())
    val pleasurePrograms: LiveData<List<LevelsItem>> = _pleasurePrograms

    private var _openedIdx = MutableLiveData(-1)
    val openedIdx: LiveData<Int> = _openedIdx

    private var _selectedTitle = MutableLiveData("")
    val selectedTitle: LiveData<String> = _selectedTitle

    private var _selectedLevel = MutableLiveData("")
    val selectedLevel: LiveData<String> = _selectedLevel


    fun requestExperiencePrograms() {
        viewModelScope.launch {
            _experiencePrograms.value = repository.requestExperiencePrograms()
        }
    }

    fun requestPleasurePrograms() {
        viewModelScope.launch {
            _pleasurePrograms.value = repository.requestPleasurePrograms()
        }
    }

    fun setOpenedIdx(idx: Int) {
        _openedIdx.value = idx
    }

    fun setSelectedTitle(title: String) {
        _selectedTitle.value = title
    }

    fun setSelectedLevel(level: String) {
        _selectedLevel.value = level
    }
}