package com.hyundai.myexperience.ui.notice.adapter

import com.hyundai.myexperience.data.entity.notice.NoticesItem

interface NoticesItemClickListener {
    fun onItemClick(notice : NoticesItem)
}