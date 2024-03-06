package com.hyundai.myexperience.ui.main.my_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.entity.user.MyPage
import com.hyundai.myexperience.data.repository.UserRepository
import com.hyundai.myexperience.utils.formatMyPageDate
import com.hyundai.myexperience.utils.getCompanyName
import com.hyundai.myexperience.utils.getLevel
import com.hyundai.myexperience.utils.getProgramName
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

    private val _commentProgramName = MutableLiveData("")
    val commentProgramName: LiveData<String> = _commentProgramName

    private val _commentContent = MutableLiveData("")
    val commentContent: LiveData<String> = _commentContent

    private val _isUpcomingNull = MutableLiveData(true)
    val isUpcomingNull: LiveData<Boolean> = _isUpcomingNull

    private val _upcomingProgramCnt = MutableLiveData<Int>(0)
    val upcomingProgramCnt: LiveData<Int> = _upcomingProgramCnt

    private val _upcomingProgramName = MutableLiveData("")
    val upcomingProgramName: LiveData<String> = _upcomingProgramName

    private val _upcomingProgramDate = MutableLiveData("")
    val upcomingProgramDate: LiveData<String> = _upcomingProgramDate

    fun checkSignedInAndRequestMyPage() {
        viewModelScope.launch {
            _isSignedIn.value = repository.getIsSigned()

            if (isSignedIn.value!!) {
                val cookie = repository.getCookie()
                repository.setCookieToConnection(cookie)

                if (repository.requestMypage() == null) {
                    repository.setIsSigned(false)
                } else {
                    requestMyPage()
                }
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

    private fun requestMyPage() {
        if (_isSignedIn.value == true) {
            viewModelScope.launch {
                val myPageResponse = repository.requestMypage()
                _myPage.value = myPageResponse
                setCommentIsEmpty()
                setCommentProgramName()
                setCommentContent()

                setUpcomingIsEmpty()
                setUpcomingProgramCnt()
                setUpcomingProgramDate()
                setUpcomingProgramName()
            }
        }
    }

    private fun setCommentIsEmpty() {
        if (myPage.value?.recentComment == null) {
            _isCommentNull.value = true
        } else {
            _isCommentNull.value = false
        }
    }

    private fun setCommentProgramName() {
        if (isCommentNull.value == true) {
            _commentProgramName.value = ""
        } else {
            _commentProgramName.value = _myPage.value?.recentComment?.programName
        }
    }

    private fun setCommentContent() {
        if (isCommentNull.value == true) {
            _commentContent.value = ""
        } else {
            _commentContent.value = _myPage.value?.recentComment?.contents
        }
    }

    private fun setUpcomingIsEmpty() {
        if (myPage.value?.upcomingClass == null) {
            _isUpcomingNull.value = true
        } else _isUpcomingNull.value = false
    }

    private fun setUpcomingProgramName() {
        if (isUpcomingNull.value == true) {
            _upcomingProgramName.value = ""
        } else {
            val category = getCompanyName(_myPage.value?.upcomingClass?.category!!) + " "
            val name = getProgramName(_myPage.value?.upcomingClass?.programName!!) + " "
            val level = getLevel(_myPage.value?.upcomingClass?.level!!)
            _upcomingProgramName.value = category + name + level
        }
    }

    private fun setUpcomingProgramDate() {
        if (isUpcomingNull.value == true) {
            _upcomingProgramDate.value = ""
        } else {
            _upcomingProgramDate.value =
                _myPage.value?.upcomingClass?.startDateTime?.formatMyPageDate()
        }
    }

    private fun setUpcomingProgramCnt() {
        if (isUpcomingNull.value == true) {
            _upcomingProgramCnt.value = 0
        } else {
            _upcomingProgramCnt.value = _myPage.value?.upcomingClass?.num
        }
    }
}