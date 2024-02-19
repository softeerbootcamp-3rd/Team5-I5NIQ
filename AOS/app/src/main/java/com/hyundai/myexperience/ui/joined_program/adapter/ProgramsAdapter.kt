package com.hyundai.myexperience.ui.joined_program.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.databinding.ItemJoinedProgramBinding
import com.hyundai.myexperience.ui.joined_program.ProgramsItem

class ProgramsAdapter (
        private var programs : List<ProgramsItem>,
    ): RecyclerView.Adapter<ProgramViewHolder>() {

    private val onItemClickListener: ProgramsItemClickListener = object :
        ProgramsItemClickListener {
        override fun onItemClick(program: ProgramsItem) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val itemBinding: ItemJoinedProgramBinding =
            ItemJoinedProgramBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProgramViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bind(programs[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return programs.size
    }
}