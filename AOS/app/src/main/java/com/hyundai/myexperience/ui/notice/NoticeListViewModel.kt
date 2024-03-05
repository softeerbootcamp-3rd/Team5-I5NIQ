package com.hyundai.myexperience.ui.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyundai.myexperience.data.NoticeRepository
import com.hyundai.myexperience.data.entity.notice.NoticesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeListViewModel @Inject constructor(private val repository: NoticeRepository): ViewModel() {
    private var _notices = MutableLiveData<List<NoticesItem>>(mutableListOf())
    val notices: LiveData<List<NoticesItem>> = _notices

    private var _notice = MutableLiveData<NoticesItem?>()
    val notice: LiveData<NoticesItem?> = _notice

    fun openNoticeDetails(notice: NoticesItem) {
        _notice.value = notice
    }

    fun noticeRequest() {
        viewModelScope.launch {
            val newNotices = repository.response()
            _notices.value = newNotices!!
        }
    }
}