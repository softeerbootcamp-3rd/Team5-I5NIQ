package com.hyundai.myexperience.ui.program_info.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.program.Comment
import com.hyundai.myexperience.databinding.ItemCommentBinding

class CommentViewHolder(private val binding: ItemCommentBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(comment: Comment, isLast: Boolean) {
        binding.tvUsername.text = comment.username
        binding.tvDate.text = comment.date
        binding.tvContent.text = comment.content

        if (isLast) binding.vLine.visibility = View.INVISIBLE
    }
}