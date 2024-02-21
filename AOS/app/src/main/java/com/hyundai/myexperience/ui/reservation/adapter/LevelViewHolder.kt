package com.hyundai.myexperience.ui.reservation.adapter

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.R
import com.hyundai.myexperience.RESERVATION_STATUS_UNABLE
import com.hyundai.myexperience.data.entity.Level
import com.hyundai.myexperience.databinding.ItemProgramLevelBinding
import com.hyundai.myexperience.ui.reservation.listener.LevelClickListener

class LevelViewHolder(private val binding: ItemProgramLevelBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(level: Level, levelClickListener: LevelClickListener) {
        binding.tvLevel.text = level.level
        binding.tvReservationStatus.text = level.status

        if (binding.tvReservationStatus.text == RESERVATION_STATUS_UNABLE) {
            binding.tvLevel.setTextColor(
                ContextCompat.getColor(binding.root.context, R.color.gray2)
            )
            binding.tvReservationStatus.setTextColor(
                ContextCompat.getColor(binding.root.context, R.color.gray2)
            )
        } else {
            binding.clItem.setOnClickListener {
                levelClickListener.onLevelClick(level.level, level.id)
            }
        }
    }
}