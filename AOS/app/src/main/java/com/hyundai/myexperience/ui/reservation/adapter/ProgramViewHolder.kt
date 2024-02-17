package com.hyundai.myexperience.ui.reservation.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.Program
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding
import com.hyundai.myexperience.ui.reservation.listener.LevelClickListener

class ProgramViewHolder(private val binding: ItemLabelBoxBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(program: Program, levelClickListener: LevelClickListener, layoutManager: LinearLayoutManager) {
        binding.rv.adapter = LevelAdapter(program.levels, levelClickListener)
        binding.rv.layoutManager = layoutManager

        binding.tvTitle.text = program.company
    }
}