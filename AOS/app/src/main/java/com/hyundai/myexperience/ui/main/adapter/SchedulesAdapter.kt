package com.hyundai.myexperience.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.schedule.SchedulesItem
import com.hyundai.myexperience.databinding.ItemScheduleBinding
import com.hyundai.myexperience.ui.main.schedule.ScheduleViewModel

class SchedulesAdapter(
    private var schedules: List<SchedulesItem>,
    private val scheduleDetailsAdapter: ScheduleDetailsAdapter,
    private val viewModel: ScheduleViewModel

) : RecyclerView.Adapter<SchedulesViewHolder>() {
    private lateinit var binding: ItemScheduleBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedulesViewHolder {
        initDataBinding(parent)

        return SchedulesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    override fun onBindViewHolder(holder: SchedulesViewHolder, position: Int) {
        holder.bind(
            schedules[position],
            position,
            scheduleDetailsAdapter,
            viewModel
        ) { notifyDataSetChanged() }
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    fun setData(data: List<SchedulesItem>) {
        schedules = data
        notifyDataSetChanged()
    }
}