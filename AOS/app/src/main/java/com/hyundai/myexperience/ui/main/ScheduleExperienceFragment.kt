package com.hyundai.myexperience.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.databinding.FragmentScheduleExperienceBinding
import com.hyundai.myexperience.ui.main.adapter.SchedulesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleExperienceFragment : Fragment() {
    private val viewModel: ScheduleViewModel by viewModels()
    private var _binding : FragmentScheduleExperienceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleExperienceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initScheduleList()
    }

    private fun initScheduleList() {
        val adapter = SchedulesAdapter(emptyList())
        binding.scheduleExperienceRv.adapter = adapter
        binding.scheduleExperienceRv.layoutManager = LinearLayoutManager(this.context)

        viewModel.scheduleExperienceRequest()

        viewModel.schedules.observe(viewLifecycleOwner) { schedules ->
            adapter.setData(schedules)
        }
    }
}
