package com.hyundai.myexperience.ui.joined_program

import androidx.lifecycle.ViewModel
import com.hyundai.myexperience.data.JoinedProgramRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JoinedProgramDetailViewModel @Inject constructor(private val repository: JoinedProgramRepository): ViewModel() {

}