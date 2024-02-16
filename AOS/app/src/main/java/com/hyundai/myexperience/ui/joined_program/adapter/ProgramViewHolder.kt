package com.hyundai.myexperience.ui.joined_program.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.databinding.ItemJoinedProgramBinding
import com.hyundai.myexperience.ui.joined_program.ProgramsItem

class ProgramViewHolder (private val itemBinding: ItemJoinedProgramBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(programsItem: ProgramsItem, itemClickListener: ProgramsItemClickListener) {
        itemBinding.joinedProgramDate.text = programsItem.programDate
        itemBinding.joinedProgramTitle.text = programsItem.programTitle
        itemBinding.rlJoinedProgramItem.setOnClickListener {
            itemClickListener.onItemClick(
                programsItem
            )
        }
    }
}