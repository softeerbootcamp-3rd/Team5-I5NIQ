package com.hyundai.myexperience.ui.joined_program.adapter

import com.hyundai.myexperience.data.entity.my_page.JoinedProgramItem
import com.hyundai.myexperience.ui.joined_program.ProgramsItem

interface ProgramsItemClickListener {
    fun onItemClick(program : JoinedProgramItem)
}