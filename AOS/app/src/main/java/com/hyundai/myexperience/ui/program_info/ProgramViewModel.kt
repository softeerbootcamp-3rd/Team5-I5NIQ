package com.hyundai.myexperience.ui.program_info

import androidx.lifecycle.ViewModel
import com.hyundai.myexperience.data.ProgramRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProgramViewModel @Inject constructor(private val repository: ProgramRepository) : ViewModel() {
}