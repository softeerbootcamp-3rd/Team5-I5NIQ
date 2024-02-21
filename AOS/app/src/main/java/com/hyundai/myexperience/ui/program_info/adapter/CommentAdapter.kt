package com.hyundai.myexperience.ui.program_info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.program.Comment
import com.hyundai.myexperience.databinding.ItemCommentBinding

class CommentAdapter(
    private val comments: List<Comment>,
) :
    RecyclerView.Adapter<CommentViewHolder>() {
    private lateinit var binding: ItemCommentBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        initDataBinding(parent)

        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val isLast = position == itemCount - 1
        holder.bind(comments[position], isLast)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }
}