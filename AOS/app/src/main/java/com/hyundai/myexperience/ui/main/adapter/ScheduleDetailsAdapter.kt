package com.hyundai.myexperience.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.ScheduleDetailsItem
import com.hyundai.myexperience.databinding.ItemScheduleDetailBinding

class ScheduleDetailsAdapter(
    private var scheduleDetails: List<ScheduleDetailsItem>,
) : RecyclerView.Adapter<ScheduleDetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDetailsViewHolder {
        val itemBinding: ItemScheduleDetailBinding =
            ItemScheduleDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleDetailsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return scheduleDetails.size
    }

    override fun onBindViewHolder(holder: ScheduleDetailsViewHolder, position: Int) {
        holder.bind(scheduleDetails[position])
    }

    fun setData(data: List<ScheduleDetailsItem>) {
        scheduleDetails = data
        notifyDataSetChanged()
    }
}
