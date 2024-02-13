package com.hyundai.myexperience.ui.notice

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoticeListViewModel : ViewModel() {
    private var _notices = MutableLiveData<List<NoticesItem>>()
    val notices: LiveData<List<NoticesItem>>
        get() = _notices

    private var _notice = MutableLiveData<NoticesItem?>()
    val notice: LiveData<NoticesItem?>
        get() = _notice

    fun openNoticeDetails(notice: NoticesItem) {
        _notice.value = notice
    }
}