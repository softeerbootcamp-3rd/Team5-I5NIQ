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
    private val toolTipEnabled = true

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

        binding.scheduleTvNotice.setOnClickListener {
            val intent = Intent(requireActivity(), NoticeListActivity::class.java)
            startActivity(intent)
        }

        binding.scheduleIvInfo.setOnClickListener {
            setToolTip()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setToolTip(){
        binding.vTooltipPoint.showAlignBottom(
            createTooltipOrientationTop(
                this.requireContext(),
                "항목을 터치해 상세한 일정을\n확인할 수 있습니다."
            )
        )
    }
}