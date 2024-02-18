package com.hyundai.myexperience.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.CarDate
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding
import com.hyundai.myexperience.ui.reservation.listener.LabelBoxClickListener

class CarDateAdapter(
    private val carDates: List<CarDate>
) :
    RecyclerView.Adapter<CarDateViewHolder>() {
    private lateinit var binding: ItemLabelBoxBinding

    private var openedIdx = -1

    private var selectedCar = ""
    private var selectedDate = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarDateViewHolder {
        initDataBinding(parent)
        initRecyclerView()

        return CarDateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarDateViewHolder, position: Int) {
        holder.bind(
            carDates[position],
            position,
            openedIdx,
            selectedCar,
            object : LabelBoxClickListener {
                override fun onBoxClick(idx: Int) {
                    openedIdx = idx

                    notifyDataSetChanged()
                }

                override fun onItemClick(content: String, type: String) {
                    selectedCar = content
                    selectedDate = type

                    notifyDataSetChanged()
                }
            })
    }

    override fun getItemCount(): Int {
        return carDates.size
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding = ItemLabelBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    private fun initRecyclerView() {
        binding.rv.layoutManager = GridLayoutManager(binding.rv.context, 4)
    }
}