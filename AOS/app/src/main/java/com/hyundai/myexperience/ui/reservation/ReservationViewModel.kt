package com.hyundai.myexperience.ui.reservation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.ReservationRepository
import com.hyundai.myexperience.data.entity.LevelsItem
import com.hyundai.myexperience.data.entity.ReservationDate
import com.hyundai.myexperience.data.entity.ReservationDatesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(private val repository: ReservationRepository) :
    ViewModel() {
    private var _step = MutableLiveData(0)
    val step: LiveData<Int> = _step

    private var _experiencePrograms = MutableLiveData<List<LevelsItem>>(listOf())
    val experiencePrograms: LiveData<List<LevelsItem>> = _experiencePrograms

    private var _pleasurePrograms = MutableLiveData<List<LevelsItem>>(listOf())
    val pleasurePrograms: LiveData<List<LevelsItem>> = _pleasurePrograms

    private var _carDates = MutableLiveData<List<ReservationDatesItem>>(listOf())
    val carDates: LiveData<List<ReservationDatesItem>> = _carDates

    private var _sessions = MutableLiveData<List<ReservationDate>>(listOf())
    val sessions: LiveData<List<ReservationDate>> = _sessions

    private var _openedProgramIdx = MutableLiveData(-1)
    val openedProgramIdx: LiveData<Int> = _openedProgramIdx

    private var _openedCarDateIdx = MutableLiveData(-1)
    val openedCarDateIdx: LiveData<Int> = _openedCarDateIdx

    private var _selectedCompany = MutableLiveData("")
    val selectedCompany: LiveData<String> = _selectedCompany

    private var _selectedLevel = MutableLiveData("")
    val selectedLevel: LiveData<String> = _selectedLevel

    private var _selectedProgramId = MutableLiveData(-1)
    val selectedProgramId: LiveData<Int> = _selectedProgramId

    private var _selectedDate = MutableLiveData("")
    val selectedDate: LiveData<String> = _selectedDate

    private var _selectedCar = MutableLiveData("")
    val selectedCar: LiveData<String> = _selectedCar

    private var _selectedCarId = MutableLiveData(-1)
    val selectedCarId: LiveData<Int> = _selectedCarId

    private var _selectedSession = MutableLiveData("")
    val selectedSession: LiveData<String> = _selectedSession

    private var _sessionSet = MutableLiveData(false)
    val sessionSet: LiveData<Boolean> = _sessionSet

    private var _selectedClassId = MutableLiveData(-1)
    val selectedClassId: LiveData<Int> = _selectedClassId

    fun setStep(step: Int) {
        _step.value = step
    }

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

    fun requestCarDates() {
        viewModelScope.launch {
            _carDates.value = repository.requestCarDates(selectedProgramId.value!!)
        }
    }

    fun requestSessions() {
        viewModelScope.launch {
            _sessions.value = repository.requestSessions(
                selectedProgramId.value!!,
                selectedCarId.value!!,
                selectedDate.value!!
            )
        }
    }

    fun setOpenedProgramIdx(idx: Int) {
        _openedProgramIdx.value = idx
    }

    fun setOpenedCarDateIdx(idx: Int) {
        _openedCarDateIdx.value = idx
    }

    fun setSelectedCompany(company: String) {
        _selectedCompany.value = company
    }

    fun setSelectedLevel(level: String) {
        _selectedLevel.value = level
    }

    fun setSelectedProgramId(id: Int) {
        _selectedProgramId.value = id
    }

    fun setSelectedDate(date: String) {
        _selectedDate.value = date
    }

    fun setSelectedCar(car: String) {
        _selectedCar.value = car
    }

    fun setSelectedCarId(id: Int) {
        _selectedCarId.value = id
    }

    fun setSelectedSession(session: String) {
        _selectedSession.value = session
        _sessionSet.value = true
    }

    fun setSelectedClassId(id: Int) {
        _selectedClassId.value = id
    }
}