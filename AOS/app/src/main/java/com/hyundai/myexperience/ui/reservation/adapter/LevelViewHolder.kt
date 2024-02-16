package com.hyundai.myexperience.ui.reservation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.Level
import com.hyundai.myexperience.databinding.ItemProgramLevelBinding
import com.hyundai.myexperience.ui.reservation.listener.LevelClickListener

class LevelViewHolder(private val binding: ItemProgramLevelBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(level: Level, levelClickListener: LevelClickListener) {
        binding.clItem.setOnClickListener {
            levelClickListener.onLevelClick(level.level)
        }

        binding.tvLevel.text = level.level
        binding.tvReservationStatus.text = level.status
    }
}