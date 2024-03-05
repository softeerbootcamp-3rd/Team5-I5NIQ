package com.hyundai.myexperience.ui.joined_program.adapter

import com.hyundai.myexperience.data.entity.user.JoinedProgramItem

interface ProgramsItemClickListener {
    fun onItemClick(program : JoinedProgramItem)
}