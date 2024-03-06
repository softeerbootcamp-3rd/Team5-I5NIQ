package com.hyundai.myexperience.ui.main.schedule

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentScheduleBinding
import com.hyundai.myexperience.ui.common.adapter.PagerFragmentAdapter
import com.hyundai.myexperience.ui.common.createTooltipOrientationTop
import com.hyundai.myexperience.ui.notice.NoticeListActivity
import com.skydoves.balloon.showAlignBottom

class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initDataBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSchedulePager()

        binding.scheduleTvNotice.setOnClickListener {
            val intent = Intent(requireActivity(), NoticeListActivity::class.java)
            startActivity(intent)
        }

        binding.scheduleIvInfo.setOnClickListener {
            showScheduleToolTip()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
    }

    private fun setSchedulePager() {
        val pagerFragmentAdapter = PagerFragmentAdapter(requireActivity())

        pagerFragmentAdapter.addFragment(ScheduleExperienceFragment())
        pagerFragmentAdapter.addFragment(SchedulePleasureFragment())

        binding.scheduleVp.adapter = pagerFragmentAdapter

        TabLayoutMediator(binding.scheduleTl, binding.scheduleVp) { tab, position ->
            tab.text = resources.getStringArray(R.array.scheduleTabTexts)[position]
        }.attach()
    }

    private fun showScheduleToolTip() {
        binding.vTooltipPoint.showAlignBottom(
            createTooltipOrientationTop(
                this.requireContext(),
                getString(R.string.schedule_info_tooltip)
            )
        )
    }
}