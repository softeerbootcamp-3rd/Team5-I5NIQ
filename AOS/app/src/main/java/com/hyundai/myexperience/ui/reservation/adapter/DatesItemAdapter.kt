package com.hyundai.myexperience.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.ReservationDatesItem
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding
import com.hyundai.myexperience.ui.reservation.listener.LabelBoxClickListener

class DatesItemAdapter(
    private var reservationDatesItems: List<ReservationDatesItem>
) :
    RecyclerView.Adapter<DatesItemViewHolder>() {
    private lateinit var binding: ItemLabelBoxBinding

    private var openedIdx = -1

    private var selectedTitle = ""
    private var selectedDate = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatesItemViewHolder {
        initDataBinding(parent)
        initRecyclerView()

        return DatesItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DatesItemViewHolder, position: Int) {
        holder.bind(
            reservationDatesItems[position],
            position,
            openedIdx,
            selectedTitle,
            object : LabelBoxClickListener {
                override fun onBoxClick(idx: Int) {
                    openedIdx = idx

                    notifyDataSetChanged()
                }

                override fun onItemClick(content: String, type: String) {
                    selectedTitle = content
                    selectedDate = type

                    notifyDataSetChanged()
                }
            })
    }

    override fun getItemCount(): Int {
        return reservationDatesItems.size
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding = ItemLabelBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    private fun initRecyclerView() {
        binding.rv.layoutManager = GridLayoutManager(binding.rv.context, 4)
    }

    fun setData(data: List<ReservationDatesItem>) {
        reservationDatesItems = data
        notifyDataSetChanged()
    }
}