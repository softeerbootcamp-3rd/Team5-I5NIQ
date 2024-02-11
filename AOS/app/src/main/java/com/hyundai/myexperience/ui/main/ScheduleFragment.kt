package com.hyundai.myexperience.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentScheduleBinding
import com.hyundai.myexperience.ui.common.PagerFragmentAdapter

class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerFragmentAdapter = PagerFragmentAdapter(requireActivity())
        pagerFragmentAdapter.addFragment(ScheduleExperienceFragment())
        pagerFragmentAdapter.addFragment(SchedulePleasureFragment())

        binding.scheduleVp.adapter = pagerFragmentAdapter

        TabLayoutMediator(binding.scheduleTl, binding.scheduleVp) { tab, position ->
            tab.text = resources.getStringArray(R.array.scheduleTabTexts)[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}