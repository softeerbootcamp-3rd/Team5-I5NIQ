package com.hyundai.myexperience.ui.reservation.listener

interface DateClickListener {
    fun onDateClick(date: String, id: Int, cost: Int, maxHeadCount: Int)
}