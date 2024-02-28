package com.hyundai.myexperience.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    fun requestSignUp(id: String, name: String, password: String) {
        viewModelScope.launch {
            repository.requestSignUp(id, name, password)
        }
    }
}