package com.hyundai.myexperience.ui.main.my_page

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.UserRepository
import com.hyundai.myexperience.data.entity.MyPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _myPage = MutableLiveData<MyPage?>()
    val myPage: LiveData<MyPage?> = _myPage

    private val _isCommentNull = MutableLiveData(true)
    val isCommentNull: LiveData<Boolean> = _isCommentNull

    private val _isSignedIn = MutableLiveData(false)
    val isSignedIn: LiveData<Boolean> = _isSignedIn

    private val _commentProgramName = MutableLiveData<String>("")
    val commentProgramName: LiveData<String> = _commentProgramName

    private val _commentContent = MutableLiveData<String>("")
    val commentContent: LiveData<String> = _commentContent

    fun requestMyPage() {
        if (_isSignedIn.value == true) {
            viewModelScope.launch {
                val myPageResponse = repository.requestMypage()
                _myPage.value = myPageResponse
                setCommentIsEmpty()
                setCommentProgramName()
                setCommentContent()
                Log.d("tag", "mypage value is ${myPage.value}")
            }
        }
    }

    fun setCommentIsEmpty() {
        if (myPage.value?.recentComment == null) {
            _isCommentNull.value = true
        }
        else _isCommentNull.value = false
    }

    fun setCommentProgramName() {
        if (isCommentNull.value == true) {
            _commentProgramName.value = ""
        }
        else _commentProgramName.value = _myPage.value?.recentComment?.programName
    }

    fun setCommentContent() {
        if (isCommentNull.value == true) {
            _commentContent.value = ""
        }
        else _commentContent.value = _myPage.value?.recentComment?.contents
    }

    fun checkSignedIn() {
        viewModelScope.launch {
            _isSignedIn.value = repository.getIsSigned()

            if (isSignedIn.value!!) {
                val cookie = repository.getCookie()
                repository.setCookieToConnection(cookie)
            }
        }
    }

    fun requestSignOut() {
        viewModelScope.launch {
            repository.requestSignOut()
            repository.setIsSigned(false)
        }

        _isSignedIn.value = false
    }

}