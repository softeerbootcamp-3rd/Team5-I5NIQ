package com.hyundai.myexperience.ui.signin

import androidx.lifecycle.ViewModel
import com.hyundai.myexperience.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
}