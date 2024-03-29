package com.hyundai.myexperience.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.schedule.ScheduleDetailsItem
import com.hyundai.myexperience.databinding.ItemScheduleDetailBinding

class ScheduleDetailsViewHolder(private val binding: ItemScheduleDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(scheduleDetailsItem: ScheduleDetailsItem) {
            binding.tvScheduleCategory.text = scheduleDetailsItem.category
            binding.tvScheduleCarList.text = scheduleDetailsItem.carList.toString() // list to string
        }
}