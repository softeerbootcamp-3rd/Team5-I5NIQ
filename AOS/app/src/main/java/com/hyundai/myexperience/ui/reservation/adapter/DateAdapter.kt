package com.hyundai.myexperience.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.ReservationDate
import com.hyundai.myexperience.databinding.ItemDateBinding
import com.hyundai.myexperience.ui.reservation.listener.DateClickListener

class DateAdapter(
    private val dates: List<ReservationDate>,
    private val dateClickListener: DateClickListener
) :
    RecyclerView.Adapter<DateViewHolder>() {
    private lateinit var binding: ItemDateBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        initDataBinding(parent)

        return DateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(dates[position], dateClickListener)
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding =
            ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }
}