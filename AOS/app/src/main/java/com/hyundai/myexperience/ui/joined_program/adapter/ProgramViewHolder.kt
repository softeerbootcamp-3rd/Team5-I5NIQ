package com.hyundai.myexperience.ui.joined_program.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramItem
import com.hyundai.myexperience.databinding.ItemJoinedProgramBinding
import com.hyundai.myexperience.ui.joined_program.ProgramsItem

class ProgramViewHolder (private val itemBinding: ItemJoinedProgramBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(programsItem: JoinedProgramItem, itemClickListener: ProgramsItemClickListener) {
        itemBinding.joinedProgramDate.text = programsItem.dateTime
        itemBinding.joinedProgramTitle.text = programsItem.programName
        itemBinding.joinedState.text = programsItem.status
        itemBinding.rlJoinedProgramItem.setOnClickListener {
            itemClickListener.onItemClick(
                programsItem
            )
        }
    }
}