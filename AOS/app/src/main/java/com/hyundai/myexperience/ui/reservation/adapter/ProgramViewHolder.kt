package com.hyundai.myexperience.ui.reservation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.Program
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding

class ProgramViewHolder(private val binding: ItemLabelBoxBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(program: Program) {
        (binding.rv.adapter as? LevelAdapter)?.updateData(program.levels)
        binding.tvTitle.text = program.company
    }
}