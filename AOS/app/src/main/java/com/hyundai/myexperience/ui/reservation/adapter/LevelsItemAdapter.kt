package com.hyundai.myexperience.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.LevelsItem
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding
import com.hyundai.myexperience.ui.reservation.listener.LabelBoxClickListener

class LevelsItemAdapter(
    private val levelsItems: List<LevelsItem>
) :
    RecyclerView.Adapter<LevelsItemViewHolder>() {
    private lateinit var binding: ItemLabelBoxBinding

    private var openedIdx = -1

    private var selectedTitle = ""
    private var selectedLevel = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelsItemViewHolder {
        initDataBinding(parent)
        initRecyclerView()

        return LevelsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LevelsItemViewHolder, position: Int) {
        holder.bind(levelsItems[position], position, openedIdx, selectedTitle, object : LabelBoxClickListener {
            override fun onBoxClick(idx: Int) {
                openedIdx = idx

                notifyDataSetChanged()
            }

            override fun onItemClick(content: String, type: String) {
                selectedTitle = content
                selectedLevel = type

                notifyDataSetChanged()
            }
        })
    }

    override fun getItemCount(): Int {
        return levelsItems.size
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding = ItemLabelBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    private fun initRecyclerView() {
        binding.rv.layoutManager = LinearLayoutManager(binding.rv.context)
    }
}