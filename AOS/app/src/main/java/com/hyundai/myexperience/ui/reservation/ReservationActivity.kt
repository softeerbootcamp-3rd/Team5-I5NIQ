package com.hyundai.myexperience.ui.reservation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
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
        pagerAdapter.addFragment(ReservationSessionHeadCountFragment())

        binding.vp.adapter = pagerAdapter

        TabLayoutMediator(binding.tl, binding.vp) { _, _ -> }.attach()

        this.setStatusBarTransparent()
        binding.cl.setPadding(0, 0, 0, this.navigationHeight())

        binding.btnNext.setOnClickListener {
            binding.fcv.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction().replace(R.id.fcv, ReservationResultFragment())
                .commit()
        }

        val toolbarLayout = binding.toolbarLayout
        toolbarLayout.toolBarTitle.text = "예약하기"
        setSupportActionBar(toolbarLayout.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reservation)
        binding.lifecycleOwner = this
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}