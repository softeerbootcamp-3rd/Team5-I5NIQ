package com.hyundai.myexperience.ui.reservation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.R
import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.data.entity.reservation.ReservationDate
import com.hyundai.myexperience.databinding.ItemDateBinding
import com.hyundai.myexperience.ui.reservation.listener.DateClickListener
import com.hyundai.myexperience.utils.formatScheduleTime

class DateViewHolder(private val binding: ItemDateBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(date: ReservationDate, levelClickListener: DateClickListener) {
        if (date.status == RESERVATION_STATUS_ABLE) {
            binding.cv.setOnClickListener {
                levelClickListener.onDateClick(date.date, date.id)
            }

            binding.tvStatus.visibility = View.GONE
        } else {
            val gray = ContextCompat.getColor(binding.root.context, R.color.gray3)

            binding.cv.strokeColor = gray
            binding.tvDate.setTextColor(gray)
            binding.tvStatus.setTextColor(gray)
        }

        binding.tvDate.text = date.date.formatScheduleTime()
    }
}