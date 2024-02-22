package com.hyundai.myexperience.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.schedule.SchedulesItem
import com.hyundai.myexperience.databinding.ItemScheduleBinding
import com.hyundai.myexperience.ui.main.ScheduleViewModel

class SchedulesViewHolder(private val itemBinding: ItemScheduleBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(schedulesItem: SchedulesItem, isLast: Boolean, adapter: ScheduleDetailsAdapter, viewModel: ScheduleViewModel) {
        if (isLast) {
            itemBinding.scheduleVDivider.visibility = View.GONE
        }

        itemBinding.scheduleTvNotice.text = schedulesItem.scheduleDate

        itemBinding.rvScheduleContents.adapter = adapter
        itemBinding.rvScheduleContents.layoutManager = LinearLayoutManager(itemBinding.root.context)

        itemBinding.root.setOnClickListener {
            viewModel.requestScheduleDetail(schedulesItem.scheduleDate)
        }
    }
}