package com.hyundai.myexperience.ui.reservation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.hyundai.myexperience.R
import com.hyundai.myexperience.RESERVATION_CAR_FIRST
import com.hyundai.myexperience.RESERVATION_DATE_FIRST
import com.hyundai.myexperience.RESERVATION_PROGRAM_FIRST
import com.hyundai.myexperience.RESERVATION_TYPE_KEY
import com.hyundai.myexperience.databinding.ActivityReservationBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.common.PagerFragmentAdapter
import com.hyundai.myexperience.ui.main.MainActivity
import com.hyundai.myexperience.ui.reservation.car_or_date_first.ReservationCarFragment
import com.hyundai.myexperience.ui.reservation.car_or_date_first.ReservationDateProgramFragment
import com.hyundai.myexperience.ui.reservation.program_first.ReservationCarDateFragment
import com.hyundai.myexperience.ui.reservation.program_first.ReservationProgramFragment
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent
import com.hyundai.myexperience.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationActivity : BaseActivity() {
    private lateinit var binding: ActivityReservationBinding
    private val reservationViewModel: ReservationViewModel by viewModels()

    private val dialog = ReservationDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        val type = intent.getIntExtra(RESERVATION_TYPE_KEY, -1)
        initPager(type)

        setOnClickBackBtn()

        binding.btnNext.setOnClickListener {
            onClickNextBtn()
        }

        binding.btnReset.setOnClickListener {
            binding.vp.setCurrentItem(0, false)
        }

        reservationViewModel.reservationFinished.observe(this) {
            if (it) {
                if (reservationViewModel.reservationSuccess.value!!) {
                    binding.fcv.visibility = View.VISIBLE

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcv, ReservationResultFragment())
                        .commit()

                    reservationViewModel.setStep(3)
                    binding.btnNext.setText(R.string.reservation_pay_btn)
                    binding.btnNext.setTextColor(ContextCompat.getColor(this, R.color.white))

                    setToolbar(
                        binding.toolbarLayout.toolbar,
                        binding.toolbarLayout.toolBarTitle,
                        resources.getString(R.string.reservation_pay_btn)
                    )

                    reservationViewModel.setSelectedClassId(-1)

                    dialog.dismiss()
                } else {
                    dialog.dismiss()

                    showToast(this, "최대 인원이 충족되어 예약할 수 없습니다. 인원 수를 조정해주세요.")
                }

            }
        }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reservation)
        binding.lifecycleOwner = this

        binding.reservationViewModel = reservationViewModel
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

        binding.vp.isUserInputEnabled = false
        binding.vp.adapter = pagerFragmentAdapter
        binding.vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvStep.text = getString(R.string.reservation_step, position + 1)
                binding.pb.progress = position + 1
            }
        })
    }

    private fun setOnClickBackBtn() {
        val callback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                val currentItem = binding.vp.currentItem

                if (currentItem in 1..2 && !reservationViewModel.reservationFinished.value!!) {
                    binding.vp.setCurrentItem(currentItem - 1, true)
                    reservationViewModel.setStep(binding.vp.currentItem)
                } else {
                    finish()
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun onClickNextBtn() {
        val currentItem = binding.vp.currentItem

        if (currentItem == 0) {
            binding.vp.setCurrentItem(currentItem + 1, true)
            reservationViewModel.requestCarDates()
        } else if (currentItem == 1) {
            binding.vp.setCurrentItem(currentItem + 1, true)
            reservationViewModel.requestSessions()
        } else if (currentItem == 2) {
            if (!reservationViewModel.reservationFinished.value!!) {
                showDialog()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
                finish()
            }
        }

        val step = binding.vp.currentItem

        reservationViewModel.setStep(step)
        reservationViewModel.setOpenedCarDateIdx(-1)
        reservationViewModel.setOpenedProgramIdx(-1)
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

    private fun showDialog() {
        dialog.show(supportFragmentManager, "ReservationDialogFragment")
    }
}