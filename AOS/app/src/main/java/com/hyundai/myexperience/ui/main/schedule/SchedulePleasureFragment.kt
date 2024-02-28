package com.hyundai.myexperience.ui.main.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.TYPE_PLEASURE
import com.hyundai.myexperience.databinding.FragmentSchedulePleasureBinding
import com.hyundai.myexperience.ui.main.adapter.ScheduleDetailsAdapter
import com.hyundai.myexperience.ui.main.adapter.SchedulesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchedulePleasureFragment : Fragment() {
    private val viewModel: ScheduleViewModel by viewModels()
    private var _binding: FragmentSchedulePleasureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSchedulePleasureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSchedules()
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        viewModel.setSelectedProgram(TYPE_PLEASURE)
    }

    private fun initSchedules() {
        viewModel.requestSchedules(TYPE_PLEASURE)
    }

    private fun initRecyclerView() {
        val detailAdapter = ScheduleDetailsAdapter(listOf())
        viewModel.scheduleDetails.observe(viewLifecycleOwner) { scheduleDetails ->
            detailAdapter.setData(scheduleDetails)
        }

        val adapter = SchedulesAdapter(listOf(), detailAdapter, viewModel)
        binding.schedulePleasureRv.adapter = adapter
        binding.schedulePleasureRv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.schedules.observe(viewLifecycleOwner) { schedules ->
            adapter.setData(schedules)
        }
    }
}