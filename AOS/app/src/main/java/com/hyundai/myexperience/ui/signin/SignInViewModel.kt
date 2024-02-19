package com.hyundai.myexperience.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    fun requestSignIn(id: String, password: String) {
        viewModelScope.launch {
            repository.requestSignIn(id, password)
        }
    }
}