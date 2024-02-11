package com.hyundai.myexperience.ui.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoticeListViewModel : ViewModel() {
    private var _notices = MutableLiveData<List<NoticesItem>>()
    val notices: LiveData<List<NoticesItem>> = _notices

    fun openNoticeDetails(notice: NoticesItem) {

    }
}