package com.hyundai.myexperience.ui.notice.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.databinding.ItemNoticeBinding
import com.hyundai.myexperience.ui.notice.NoticesItem

class NoticeViewHolder(private val itemBinding: ItemNoticeBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(noticesItem: NoticesItem, itemClickListener: NoticesItemClickListener) {
        itemBinding.date.text = noticesItem.date
        itemBinding.noticeTitle.text = noticesItem.noticeTitle
        itemBinding.rlNoticeItem.setOnClickListener { itemClickListener.onItemClick(noticesItem) }
    }
}