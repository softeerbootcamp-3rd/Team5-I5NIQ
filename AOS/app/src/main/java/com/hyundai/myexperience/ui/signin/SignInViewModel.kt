package com.hyundai.myexperience.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.UserRepository
import com.hyundai.myexperience.data.remote.ServerConnection
import com.hyundai.myexperience.utils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    fun requestSignIn(id: String, password: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            val result = repository.requestSignIn(id, password)

            if (result) {
                repository.setIsSigned(true)
                repository.setCookie(ServerConnection.getCookie() ?: "")
                onSuccess()
            } else {
                onFailure()
            }
        }
    }
}