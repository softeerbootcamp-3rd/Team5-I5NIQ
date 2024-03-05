package com.hyundai.myexperience.ui.program_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.repository.ProgramRepository
import com.hyundai.myexperience.data.entity.program.Comment
import com.hyundai.myexperience.data.entity.program.ProgramCar
import com.hyundai.myexperience.data.entity.program.ProgramConfData
import com.hyundai.myexperience.data.entity.program.ProgramMajorData
import com.hyundai.myexperience.data.entity.program.ProgramTrack
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

    private val _selectedCar = MutableLiveData<ProgramCar>()
    val selectedCar: LiveData<ProgramCar> = _selectedCar

    private val _tracks = MutableLiveData<List<ProgramTrack>>()
    val tracks: LiveData<List<ProgramTrack>> = _tracks

    private val _selectedTrack = MutableLiveData<ProgramTrack>()
    val selectedTrack: LiveData<ProgramTrack> = _selectedTrack

    private val _comments = MutableLiveData<List<Comment>>(listOf())
    val comments: LiveData<List<Comment>> = _comments

    fun setId(id: Int) {
        _id.value = id
    }

    fun requestProgramMajorData() {
        viewModelScope.launch {
            _majorData.value = repository.requestProgramMajorData(id.value!!)
        }
    }

    fun requestProgramConfData() {
        viewModelScope.launch {
            _confData.value = repository.requestProgramConfData(id.value!!)
            _selectedCar.value = confData.value?.cars?.get(0)
        }
    }

    fun requestProgramTracks() {
        viewModelScope.launch {
            _tracks.value = repository.requestProgramTracks(id.value!!)
            _selectedTrack.value = tracks.value?.get(0)
        }
    }

    fun requestProgramComments() {
        viewModelScope.launch {
            _comments.value = repository.requestProgramComments(id.value!!)
        }
    }

    fun setSelectedCar(carIdx: Int) {
        _selectedCar.value = confData.value?.cars?.get(carIdx)
    }

    fun setSelectedTrack(trackIdx: Int) {
        _selectedTrack.value = _tracks.value?.get(trackIdx)
    }
}