package com.hyundai.myexperience.ui.program_info

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.hyundai.myexperience.COMPANY_GENESIS
import com.hyundai.myexperience.COMPANY_HYUNDAI
import com.hyundai.myexperience.COMPANY_KIA
import com.hyundai.myexperience.COMPANY_TYPE_KEY
import com.hyundai.myexperience.PROGRAM_TYPE_KEY
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityProgramInfoBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.common.PagerFragmentAdapter
import com.hyundai.myexperience.ui.reservation_entrance.ReservationEntranceActivity
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgramInfoActivity : BaseActivity() {
    private lateinit var binding: ActivityProgramInfoBinding

    private val programViewModel: ProgramViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        initPager()

        binding.btnReservation.setOnClickListener {
            val intent = Intent(this, ReservationEntranceActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_program_info)
        binding.lifecycleOwner = this
    }

    private fun initScreen() {
        this.setStatusBarTransparent()
        binding.clMain.setPadding(0, 0, 0, navigationHeight())

        setToolbar(binding.toolbarLayout.toolbar, binding.toolbarLayout.toolBarTitle, "")

        val program = intent.getStringExtra(PROGRAM_TYPE_KEY)
        binding.tvLevel.text = program
        binding.tvLevelSmall.text = program

        val company = intent.getStringExtra(COMPANY_TYPE_KEY)
        binding.tvProgramTop.text = company

        val image = when (company) {
            COMPANY_HYUNDAI -> R.drawable.program_category_iv_hyundai
            COMPANY_KIA -> R.drawable.program_category_iv_kia
            COMPANY_GENESIS -> R.drawable.program_category_iv_genesis
            else -> R.drawable.program_category_iv_hmg
        }
        binding.ivBackground.setImageResource(image)

        val text = when (company) {
            COMPANY_HYUNDAI -> resources.getString(R.string.program_category_explain_hyundai)
            COMPANY_KIA -> resources.getString(R.string.program_category_explain_kia)
            COMPANY_GENESIS -> resources.getString(R.string.program_category_explain_genesis)
            else -> resources.getString(R.string.program_category_explain_hmg)
        }
        binding.tvExplain.text = text
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
                    binding.ivBackground.visibility = View.VISIBLE
                } else {
                    binding.clTop.visibility = View.GONE
                    binding.ivBackground.visibility = View.INVISIBLE
                }
            }
        })
    }
}