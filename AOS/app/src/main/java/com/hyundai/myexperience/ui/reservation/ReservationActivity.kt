package com.hyundai.myexperience.ui.reservation

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.hyundai.myexperience.R
import com.hyundai.myexperience.RESERVATION_CAR_FIRST
import com.hyundai.myexperience.RESERVATION_DATE_FIRST
import com.hyundai.myexperience.RESERVATION_PROGRAM_FIRST
import com.hyundai.myexperience.RESERVATION_TYPE_KEY
import com.hyundai.myexperience.databinding.ActivityReservationBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.common.PagerFragmentAdapter
import com.hyundai.myexperience.ui.reservation.car_or_date_first.ReservationCarFragment
import com.hyundai.myexperience.ui.reservation.car_or_date_first.ReservationDateProgramFragment
import com.hyundai.myexperience.ui.reservation.program_first.ReservationCarDateFragment
import com.hyundai.myexperience.ui.reservation.program_first.ReservationProgramFragment
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent

class ReservationActivity : BaseActivity() {
    private lateinit var binding: ActivityReservationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        val type = intent.getIntExtra(RESERVATION_TYPE_KEY, -1)
        initPager(type)

        binding.btnNext.setOnClickListener {
            binding.fcv.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction().replace(R.id.fcv, ReservationResultFragment())
                .commit()
        }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reservation)
        binding.lifecycleOwner = this
    }

    private fun initScreen() {
        this.setStatusBarTransparent()
        binding.cl.setPadding(0, 0, 0, this.navigationHeight())

        setToolbar(
            binding.toolbarLayout.toolbar,
            binding.toolbarLayout.toolBarTitle,
            resources.getString(R.string.main_reservation)
        )
    }

    private fun initPager(type: Int) {
        val pagerFragmentAdapter = PagerFragmentAdapter(this)

        val fragments = getFragmentsByType(type)
        for (fragment in fragments) {
            pagerFragmentAdapter.addFragment(fragment)
        }
        pagerFragmentAdapter.addFragment(ReservationSessionHeadCountFragment())

        binding.vp.adapter = pagerFragmentAdapter
        binding.vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvStep.text = getString(R.string.reservation_step, position + 1)
                if (position >= 2) binding.vPriceBackground.visibility = View.VISIBLE
            }
        })

        TabLayoutMediator(binding.tl, binding.vp) { _, _ -> }.attach()
    }

    private fun getFragmentsByType(type: Int): List<Fragment> {
        return when (type) {
            RESERVATION_PROGRAM_FIRST -> listOf(
                ReservationProgramFragment(),
                ReservationCarDateFragment()
            )

            RESERVATION_DATE_FIRST -> listOf(
                ReservationCarFragment(),
                ReservationDateProgramFragment()
            )

            RESERVATION_CAR_FIRST -> listOf(
                ReservationDateProgramFragment(),
                ReservationCarFragment()
            )

            else -> listOf()
        }
    }

}