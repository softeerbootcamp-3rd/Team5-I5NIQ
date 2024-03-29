package com.hyundai.myexperience.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.RESERVATION_STATUS_UNABLE
import com.hyundai.myexperience.data.repository.ReservationRepository
import com.hyundai.myexperience.data.entity.reservation.LevelsItem
import com.hyundai.myexperience.data.entity.reservation.ReservationCar
import com.hyundai.myexperience.data.entity.reservation.ReservationDate
import com.hyundai.myexperience.data.entity.reservation.ReservationDatesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(private val repository: ReservationRepository) :
    ViewModel() {
    private var _type = MutableLiveData(0)
    val type: LiveData<Int> = _type

    private var _step = MutableLiveData(0)
    val step: LiveData<Int> = _step

    private var _reservationFinished = MutableLiveData(false)
    val reservationFinished: LiveData<Boolean> = _reservationFinished

    private var _reservationSuccess = MutableLiveData(false)
    val reservationSuccess: LiveData<Boolean> = _reservationSuccess

    private var _experiencePrograms = MutableLiveData<List<LevelsItem>>(listOf())
    val experiencePrograms: LiveData<List<LevelsItem>> = _experiencePrograms

    private var _pleasurePrograms = MutableLiveData<List<LevelsItem>>(listOf())
    val pleasurePrograms: LiveData<List<LevelsItem>> = _pleasurePrograms

    private var _carDates = MutableLiveData<List<ReservationDatesItem>>(listOf())
    val carDates: LiveData<List<ReservationDatesItem>> = _carDates

    private var _dates = MutableLiveData<List<ReservationDate>>(listOf())
    val dates: LiveData<List<ReservationDate>> = _dates

    private var _cars = MutableLiveData<List<ReservationCar>>(listOf())
    val cars: LiveData<List<ReservationCar>> = _cars

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

    private var _selectedMaxHeadCount = MutableLiveData(1)
    val selectedMaxHeadCount: LiveData<Int> = _selectedMaxHeadCount

    private var _selectedCost = MutableLiveData(0)
    val selectedCost: LiveData<Int> = _selectedCost

    private var _selectedHeadCount = MutableLiveData(1)
    val selectedHeadCount: LiveData<Int> = _selectedHeadCount

    private var _participation = MutableLiveData(true)
    val participation: LiveData<Boolean> = _participation

    fun setType(type: Int) {
        _type.value = type
    }

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

    fun requestExperienceProgramsByDate() {
        viewModelScope.launch {
            _experiencePrograms.value = repository.requestExperienceProgramsByDate(selectedDate.value!!)
        }
    }

    fun requestPleasureProgramsByDate() {
        viewModelScope.launch {
            _pleasurePrograms.value = repository.requestPleasureProgramsByDate(selectedDate.value!!)
        }
    }

    fun requestCarDates() {
        viewModelScope.launch {
            _carDates.value = repository.requestCarDates(selectedProgramId.value!!)
        }
    }

    fun requestDates() {
        viewModelScope.launch {
            _dates.value = repository.requestDates()
        }
    }

    fun requestCarsByProgram() {
        viewModelScope.launch {
            repository.requestCarsByProgram()
        }
    }

    fun requestCars() {
        viewModelScope.launch {
//            _cars.value = repository.requestCars()
            _cars.value = listOf(
                ReservationCar(
                    1,
                    "아반떼 N Line",
                    RESERVATION_STATUS_ABLE
                ),
                ReservationCar(
                    2,
                    "아반떼 N DCT",
                    RESERVATION_STATUS_UNABLE
                )
            )
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

    fun requestReservation() {
        viewModelScope.launch {
            _reservationSuccess.value =
                repository.requestReservation(selectedClassId.value!!, selectedHeadCount.value!!)

            if (reservationSuccess.value == true) {
                _reservationFinished.value = true
            } else {
                _reservationFinished.value = true
                _reservationFinished.value = false
            }
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

    fun setSelectedMaxHeadCount(maxHeadCount: Int) {
        _selectedMaxHeadCount.value = maxHeadCount
    }

    fun setSelectedCost(cost: Int) {
        _selectedCost.value = cost
    }

    fun setSelectedHeadCount(headCount: Int) {
        _selectedHeadCount.value = headCount
    }

    fun setParticipation(participation: Boolean) {
        _participation.value = participation
    }

    fun reset() {
        _step.value = 0
        _openedProgramIdx.value = -1
        _openedCarDateIdx.value = -1
        _selectedCompany.value = ""
        _selectedLevel.value = ""
        _selectedProgramId.value = -1
        _selectedDate.value = ""
        _selectedCar.value = ""
        _selectedCarId.value = -1
        _selectedSession.value = ""
        _sessionSet.value = false
        _selectedClassId.value = -1
        _selectedMaxHeadCount.value = 1
        _selectedCost.value = 0
        _selectedHeadCount.value = 1
        _participation.value = true
    }

}