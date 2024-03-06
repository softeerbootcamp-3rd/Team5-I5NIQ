package com.hyundai.myexperience.ui.main.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.TYPE_EXPERIENCE
import com.hyundai.myexperience.databinding.FragmentScheduleExperienceBinding
import com.hyundai.myexperience.ui.main.adapter.ScheduleDetailsAdapter
import com.hyundai.myexperience.ui.main.adapter.SchedulesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleExperienceFragment : Fragment() {
    private var _binding: FragmentScheduleExperienceBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ScheduleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initDataBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.requestSchedules(TYPE_EXPERIENCE)
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.setSelectedProgram(TYPE_EXPERIENCE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentScheduleExperienceBinding.inflate(inflater, container, false)
    }

    private fun initRecyclerView() {
        val detailAdapter = ScheduleDetailsAdapter(listOf())
        viewModel.scheduleDetails.observe(viewLifecycleOwner) { scheduleDetails ->
            detailAdapter.setData(scheduleDetails)
        }

        val adapter = SchedulesAdapter(listOf(), detailAdapter, viewModel)
        binding.scheduleExperienceRv.adapter = adapter
        binding.scheduleExperienceRv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.schedules.observe(viewLifecycleOwner) { schedules ->
            adapter.setData(schedules)
        }
    }
}
