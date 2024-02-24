package com.hyundai.myexperience.ui.joined_program.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramItem
import com.hyundai.myexperience.databinding.ItemJoinedProgramBinding
import com.hyundai.myexperience.ui.joined_program.ProgramsItem

class ProgramViewHolder(private val itemBinding: ItemJoinedProgramBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(programsItem: JoinedProgramItem, itemClickListener: ProgramsItemClickListener) {
        itemBinding.apply {
            joinedProgramDate.text = programsItem.dateTime
            joinedProgramTitle.text = programsItem.programName
            joinedState.text = programsItem.status
            joinedState.setTextColor(getColorByStatus(programsItem.status))
            rlJoinedProgramItem.setOnClickListener { itemClickListener.onItemClick(programsItem) }
        }
    }

    private fun getColorByStatus(status: String): Int {
        return when (status) {
            "참여완료" -> ContextCompat.getColor(itemView.context, R.color.blue)
            "결제완료" -> ContextCompat.getColor(itemView.context, R.color.purple)
            else -> ContextCompat.getColor(itemView.context, R.color.black)
        }

    }
}