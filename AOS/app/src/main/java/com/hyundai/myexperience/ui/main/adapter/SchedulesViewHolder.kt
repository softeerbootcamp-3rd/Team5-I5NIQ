package com.hyundai.myexperience.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.schedule.SchedulesItem
import com.hyundai.myexperience.databinding.ItemScheduleBinding
import com.hyundai.myexperience.ui.main.schedule.ScheduleViewModel
import com.hyundai.myexperience.utils.formatScheduleDate

class SchedulesViewHolder(private val itemBinding: ItemScheduleBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(
        schedulesItem: SchedulesItem,
        idx: Int,
        adapter: ScheduleDetailsAdapter,
        viewModel: ScheduleViewModel,
        notify: () -> Unit
    ) {
        if (idx == viewModel.selectedIdx.value) {
            itemBinding.rvScheduleContents.visibility = View.VISIBLE
            itemBinding.ivIcon.rotation = 180f
        } else {
            itemBinding.rvScheduleContents.visibility = View.GONE
            itemBinding.ivIcon.rotation = 0f
        }

        itemBinding.scheduleTvNotice.text = schedulesItem.scheduleDate.formatScheduleDate("yyyy년 M월 d일")

        itemBinding.rvScheduleContents.adapter = adapter
        itemBinding.rvScheduleContents.layoutManager = LinearLayoutManager(itemBinding.root.context)

        itemBinding.root.setOnClickListener {
            viewModel.requestScheduleDetail(schedulesItem.scheduleDate) { notify() }

            if (viewModel.selectedIdx.value == idx) {
                viewModel.setSelectedIdx(-1)
            } else {
                viewModel.setSelectedIdx(idx)
            }
        }
    }
}