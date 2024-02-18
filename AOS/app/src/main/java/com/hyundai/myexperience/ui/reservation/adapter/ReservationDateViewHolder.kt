package com.hyundai.myexperience.ui.reservation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.data.entity.ReservationDate
import com.hyundai.myexperience.databinding.ItemDateBinding
import com.hyundai.myexperience.ui.reservation.listener.DateClickListener

class ReservationDateViewHolder(private val binding: ItemDateBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(date: ReservationDate, levelClickListener: DateClickListener) {
        if (date.status == RESERVATION_STATUS_ABLE) {
            binding.cv.setOnClickListener {
                levelClickListener.onDateClick(date.date)
            }

            binding.tvStatus.visibility = View.GONE
        }

        binding.tvDate.text = date.date
    }
}