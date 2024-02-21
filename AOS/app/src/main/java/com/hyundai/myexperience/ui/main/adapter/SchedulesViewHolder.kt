package com.hyundai.myexperience.ui.main.adapter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.databinding.ItemScheduleBinding
import com.hyundai.myexperience.data.entity.SchedulesItem
import com.hyundai.myexperience.ui.main.ScheduleViewModel

class SchedulesViewHolder (val itemBinding: ItemScheduleBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(schedulesItem: SchedulesItem, adapter: ScheduleDetailsAdapter) {
        itemBinding.scheduleTvNotice.text = schedulesItem.scheduleDate

        itemBinding.rvScheduleContents.adapter = adapter
        itemBinding.rvScheduleContents.layoutManager = LinearLayoutManager(itemBinding.root.context)
    }
}