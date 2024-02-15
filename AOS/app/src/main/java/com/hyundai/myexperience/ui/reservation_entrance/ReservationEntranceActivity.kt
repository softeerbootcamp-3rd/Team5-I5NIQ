package com.hyundai.myexperience.ui.reservation_entrance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.R
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
            val intent = Intent(this, ReservationActivity::class.java)
            startActivity(intent)
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
}