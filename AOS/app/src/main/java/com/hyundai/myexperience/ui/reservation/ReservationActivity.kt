package com.hyundai.myexperience.ui.reservation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityReservationBinding
import com.hyundai.myexperience.ui.common.PagerAdapter
import com.hyundai.myexperience.ui.reservation.program_first.ReservationCarDateFragment
import com.hyundai.myexperience.ui.reservation.program_first.ReservationProgramFragment
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent

class ReservationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()

        val pagerAdapter = PagerAdapter(this)
        pagerAdapter.addFragment(ReservationProgramFragment())
        pagerAdapter.addFragment(ReservationCarDateFragment())

        binding.vp.adapter = pagerAdapter

        TabLayoutMediator(binding.tl, binding.vp) { _, _ -> }.attach()

        this.setStatusBarTransparent()
        binding.cl.setPadding(0, 0, 0, this.navigationHeight())
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reservation)
        binding.lifecycleOwner = this
    }
}