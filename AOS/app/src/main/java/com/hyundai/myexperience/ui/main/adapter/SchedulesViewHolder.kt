package com.hyundai.myexperience.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.databinding.ItemScheduleBinding
import com.hyundai.myexperience.ui.main.SchedulesItem

class SchedulesViewHolder (val itemBinding: ItemScheduleBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(schedulesItem: SchedulesItem) {
        itemBinding.scheduleTvNotice.text = schedulesItem.scheduleDate
    }
}