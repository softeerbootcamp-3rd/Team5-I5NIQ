package com.hyundai.myexperience.ui.notice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.databinding.ItemNoticeBinding
import com.hyundai.myexperience.ui.notice.NoticeListViewModel
import com.hyundai.myexperience.data.entity.NoticesItem

class NoticesAdapter (
    private val noticesListViewModel: NoticeListViewModel,
    private var notices : List<NoticesItem>,
):
    RecyclerView.Adapter<NoticeViewHolder>() {

    private val onItemClickListener: NoticesItemClickListener = object : NoticesItemClickListener {
        override fun onItemClick(notice: NoticesItem) {
            noticesListViewModel.openNoticeDetails(notice)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val itemBinding: ItemNoticeBinding =
            ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(notices[position], onItemClickListener)

    }

    override fun getItemCount(): Int {
        return notices.size
    }

    fun setData(data: List<NoticesItem>) {
        notices = data
        notifyDataSetChanged()
    }
}