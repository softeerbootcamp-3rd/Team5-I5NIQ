package com.hyundai.myexperience.ui.reservation_entrance

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.FRAGMENT_IDX_KEY
import com.hyundai.myexperience.R
import com.hyundai.myexperience.RESERVATION_CAR_FIRST
import com.hyundai.myexperience.RESERVATION_DATE_FIRST
import com.hyundai.myexperience.RESERVATION_PROGRAM_FIRST
import com.hyundai.myexperience.RESERVATION_TYPE_KEY
import com.hyundai.myexperience.databinding.ActivityReservationEntranceBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.main.MainActivity
import com.hyundai.myexperience.ui.reservation.ReservationActivity
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent
import com.hyundai.myexperience.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationEntranceActivity : BaseActivity() {
    private lateinit var binding: ActivityReservationEntranceBinding
    private val reservationEntranceViewModel: ReservationEntranceViewModel by viewModels()

    private val dialog = QueueDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        initQueueObserver()

        reservationEntranceViewModel.checkSignedIn()

        binding.reservationClProgram.setOnClickListener {
            val intent = Intent(this, ReservationActivity::class.java)
            intent.putExtra(RESERVATION_TYPE_KEY, reservationEntranceViewModel.selectionType.value)
            startActivity(intent)

//            startReservation(RESERVATION_PROGRAM_FIRST)
        }

        binding.reservationCvProgram.setOnClickListener {
            startReservation(RESERVATION_PROGRAM_FIRST)
        }

        binding.reservationClDate.setOnClickListener {
            startReservation(RESERVATION_DATE_FIRST)
        }

        binding.reservationCvDate.setOnClickListener {
            startReservation(RESERVATION_DATE_FIRST)
        }

        binding.reservationClCar.setOnClickListener {
            startReservation(RESERVATION_CAR_FIRST)
        }

        binding.reservationCvCar.setOnClickListener {
            startReservation(RESERVATION_CAR_FIRST)
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

    private fun initQueueObserver() {
        reservationEntranceViewModel.queueingFinished.observe(this) {
            if (it) {
                dialog.dismiss()

                val intent = Intent(this, ReservationActivity::class.java)
                intent.putExtra(RESERVATION_TYPE_KEY, reservationEntranceViewModel.selectionType.value)
                startActivity(intent)
            }
        }
    }

    private fun startReservation(type: Int) {
        if (!reservationEntranceViewModel.isSignedIn.value!!) {
            moveToMyPage()
            showToast(this, resources.getString(R.string.reservation_need_login_toast))
        } else {
            reservationEntranceViewModel.setSelectionType(type)
            reservationEntranceViewModel.startDataReceiving()
            showDialog()
        }
    }

    private fun moveToMyPage() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        intent.putExtra(FRAGMENT_IDX_KEY, 3)
        startActivity(intent)
        finish()
    }

    private fun showDialog() {
        dialog.show(supportFragmentManager, "QueueDialogFragment")
    }
}