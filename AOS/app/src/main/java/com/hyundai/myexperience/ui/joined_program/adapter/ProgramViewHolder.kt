package com.hyundai.myexperience.ui.joined_program.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramItem
import com.hyundai.myexperience.databinding.ItemJoinedProgramBinding
import com.hyundai.myexperience.ui.joined_program.ProgramsItem
import com.hyundai.myexperience.utils.formatMyPageDate
import com.hyundai.myexperience.utils.getCompanyName
import com.hyundai.myexperience.utils.getLevel
import com.hyundai.myexperience.utils.getProgramName

class ProgramViewHolder(private val itemBinding: ItemJoinedProgramBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(programsItem: JoinedProgramItem, itemClickListener: ProgramsItemClickListener) {
        itemBinding.apply {
            joinedProgramDate.text = programsItem.dateTime.formatMyPageDate()
            joinedProgramTitle.text =
                getProgramTitle(programsItem.category, programsItem.programName, programsItem.level)
            joinedState.text = programsItem.status
            joinedState.setTextColor(getColorByStatus(programsItem.status))
            rlJoinedProgramItem.setOnClickListener { itemClickListener.onItemClick(programsItem) }
        }
    }

    private fun getColorByStatus(status: String): Int {
        return when (status) {
            "결제완료" -> ContextCompat.getColor(itemView.context, R.color.blue)
            "참여완료" -> ContextCompat.getColor(itemView.context, R.color.purple)
            else -> ContextCompat.getColor(itemView.context, R.color.black)
        }

    }

    private fun getProgramTitle(category: String, name: String, level: String): String {
        return getCompanyName(category) + " " + getProgramName(name) + " " + getLevel(level)
    }
}