package com.hyundai.myexperience.ui.signup

import androidx.lifecycle.ViewModel
import com.hyundai.myexperience.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

}