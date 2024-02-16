package com.hyundai.myexperience.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.databinding.FragmentSchedulePleasureBinding
import com.hyundai.myexperience.ui.main.adapter.SchedulesAdapter

class SchedulePleasureFragment : Fragment() {
    private var _binding : FragmentSchedulePleasureBinding? = null
    private val binding get() = _binding!!
    private lateinit var schedulesList: List<SchedulesItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSchedulePleasureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        schedulesList = listOf(
            SchedulesItem("2024년 2월 6일"),
            SchedulesItem("2024년 2월 12일"),
            SchedulesItem("2024년 2월 13일"),
            SchedulesItem("2024년 2월 14일"),
            SchedulesItem("2024년 2월 22일"),
            SchedulesItem("2024년 3월 4일"),
        )

        val adapter = SchedulesAdapter(schedulesList)
        binding.schedulePleasureRv.adapter = adapter
        binding.schedulePleasureRv.layoutManager = LinearLayoutManager(this.context)
    }
}