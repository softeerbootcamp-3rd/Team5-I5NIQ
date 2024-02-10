package com.hyundai.myexperience.ui.program_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityProgramInfoBinding
import com.hyundai.myexperience.ui.common.PagerAdapter
import com.hyundai.myexperience.ui.reservation.ReservationSessionHeadCountFragment
import com.hyundai.myexperience.ui.reservation.program_first.ReservationCarDateFragment
import com.hyundai.myexperience.ui.reservation.program_first.ReservationProgramFragment
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent

class ProgramInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgramInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()

        this.setStatusBarTransparent()
        binding.clMain.setPadding(0, 0, 0, navigationHeight())

        val pagerAdapter = PagerAdapter(this)
        pagerAdapter.addFragment(ProgramMajorFragment())
        pagerAdapter.addFragment(ProgramConfFragment())
        pagerAdapter.addFragment(ProgramTrackFragment())
        pagerAdapter.addFragment(ProgramCommentFragment())

        binding.vp.adapter = pagerAdapter

        TabLayoutMediator(binding.tl, binding.vp) { tab, position ->
            tab.text = resources.getStringArray(R.array.programTabTexts)[position]
        }.attach()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_program_info)
        binding.lifecycleOwner = this
    }
}