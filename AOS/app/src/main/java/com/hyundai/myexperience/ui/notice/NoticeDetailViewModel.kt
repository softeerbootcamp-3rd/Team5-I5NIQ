package com.hyundai.myexperience.ui.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.NoticeRepository
import com.hyundai.myexperience.data.entity.NoticeDetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeDetailViewModel @Inject constructor(private val repository: NoticeRepository): ViewModel() {
    private var _notice = MutableLiveData(NoticeDetailItem(0, "", "", "", "", "")) // 초기값을 설정해줘야 함
    val notice: LiveData<NoticeDetailItem?> = _notice

    private var _noticeId = MutableLiveData<Int>()
    val noticeId: LiveData<Int> = _noticeId

    fun noticeDetailRequest() {
        viewModelScope.launch {
            val response = repository.response(noticeId.value!!)
            _notice.value = response
        }
    }

    fun setNoticeId(id: Int){
        _noticeId.value = id
    }
}