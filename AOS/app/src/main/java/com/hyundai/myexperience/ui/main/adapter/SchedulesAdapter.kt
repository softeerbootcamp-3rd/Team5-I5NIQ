package com.hyundai.myexperience.ui.main.adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.databinding.ItemScheduleBinding
import com.hyundai.myexperience.data.entity.SchedulesItem

class SchedulesAdapter (
    private var schedules : List<SchedulesItem>,
) : RecyclerView.Adapter<SchedulesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedulesViewHolder {
        val itemBinding: ItemScheduleBinding =
            ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchedulesViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    override fun onBindViewHolder(holder: SchedulesViewHolder, position: Int) {
        holder.bind(schedules[position])
        if (position == itemCount - 1) {
            holder.itemBinding.scheduleVDivider.visibility = GONE
        }
    }

    fun setData(data: List<SchedulesItem>) {
        schedules = data
        notifyDataSetChanged()
    }
}