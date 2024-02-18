package com.hyundai.myexperience.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.Program
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding
import com.hyundai.myexperience.ui.reservation.listener.LabelBoxClickListener

class ProgramAdapter(
    private val programs: List<Program>
) :
    RecyclerView.Adapter<ProgramViewHolder>() {
    private lateinit var binding: ItemLabelBoxBinding

    private var openedIdx = -1

    private var selectedCompany = ""
    private var selectedLevel = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        initDataBinding(parent)
        initRecyclerView()

        return ProgramViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bind(programs[position], position, openedIdx, selectedCompany, object : LabelBoxClickListener {
            override fun onBoxClick(idx: Int) {
                openedIdx = idx

                notifyDataSetChanged()
            }

            override fun onItemClick(content: String, type: String) {
                selectedCompany = content
                selectedLevel = type

                notifyDataSetChanged()
            }
        })
    }

    override fun getItemCount(): Int {
        return programs.size
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding = ItemLabelBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    private fun initRecyclerView() {
        binding.rv.layoutManager = LinearLayoutManager(binding.rv.context)
    }
}