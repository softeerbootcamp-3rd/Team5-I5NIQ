package com.hyundai.myexperience.ui.program_info

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.hyundai.myexperience.PROGRAM_TYPE_KEY
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityProgramInfoBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.common.PagerFragmentAdapter
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent

class ProgramInfoActivity : BaseActivity() {
    private lateinit var binding: ActivityProgramInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        initPager()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_program_info)
        binding.lifecycleOwner = this
    }

    private fun initScreen() {
        this.setStatusBarTransparent()
        binding.clMain.setPadding(0, 0, 0, navigationHeight())

        val level = intent.getStringExtra(PROGRAM_TYPE_KEY)
        binding.tvLevel.text = level
    }

    private fun initPager() {
        val pagerFragmentAdapter = PagerFragmentAdapter(this)
        pagerFragmentAdapter.addFragment(ProgramMajorFragment())
        pagerFragmentAdapter.addFragment(ProgramConfFragment())
        pagerFragmentAdapter.addFragment(ProgramTrackFragment())
        pagerFragmentAdapter.addFragment(ProgramCommentFragment())

        binding.vp.adapter = pagerFragmentAdapter

        TabLayoutMediator(binding.tl, binding.vp) { tab, position ->
            tab.text = resources.getStringArray(R.array.programTabTexts)[position]
        }.attach()

        binding.vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0) {
                    binding.clTop.visibility = View.VISIBLE
                } else {
                    binding.clTop.visibility = View.GONE
                }
            }
        })
    }
}