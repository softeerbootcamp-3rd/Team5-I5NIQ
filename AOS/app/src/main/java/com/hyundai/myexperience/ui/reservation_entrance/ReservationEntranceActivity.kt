package com.hyundai.myexperience.ui.reservation_entrance

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.R
import com.hyundai.myexperience.RESERVATION_CAR_FIRST
import com.hyundai.myexperience.RESERVATION_DATE_FIRST
import com.hyundai.myexperience.RESERVATION_PROGRAM_FIRST
import com.hyundai.myexperience.RESERVATION_TYPE_KEY
import com.hyundai.myexperience.databinding.ActivityReservationEntranceBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.reservation.ReservationActivity
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent

class ReservationEntranceActivity : BaseActivity() {
    private lateinit var binding: ActivityReservationEntranceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        binding.reservationClProgram.setOnClickListener {
            startReservationActivity(RESERVATION_PROGRAM_FIRST)
        }

        binding.reservationCvProgram.setOnClickListener {
            startReservationActivity(RESERVATION_PROGRAM_FIRST)
        }

        binding.reservationClDate.setOnClickListener {
            startReservationActivity(RESERVATION_DATE_FIRST)
        }

        binding.reservationCvDate.setOnClickListener {
            startReservationActivity(RESERVATION_DATE_FIRST)
        }

        binding.reservationClCar.setOnClickListener {
            startReservationActivity(RESERVATION_CAR_FIRST)
        }

        binding.reservationCvCar.setOnClickListener {
            startReservationActivity(RESERVATION_CAR_FIRST)
        }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reservation_entrance)
        binding.lifecycleOwner = this
    }

    private fun initScreen() {
        this.setStatusBarTransparent()
        binding.cl.setPadding(0, 0, 0, navigationHeight())

        setToolbar(binding.toolbarLayout.toolbar, binding.toolbarLayout.toolBarTitle, "")
    }

    private fun startReservationActivity(type: Int) {
        val intent = Intent(this, ReservationActivity::class.java)
        intent.putExtra(RESERVATION_TYPE_KEY, type)
        startActivity(intent)
    }
}