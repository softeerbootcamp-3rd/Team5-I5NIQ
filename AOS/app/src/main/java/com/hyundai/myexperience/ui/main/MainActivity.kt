package com.hyundai.myexperience.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hyundai.myexperience.FRAGMENT_IDX_KEY
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityMainBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.main.my_page.MyPageFragment
import com.hyundai.myexperience.ui.main.my_page.MyPageViewModel
import com.hyundai.myexperience.ui.main.schedule.ScheduleFragment
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myPageViewModel: MyPageViewModel by viewModels()

    private val fragmentIds = listOf(
        R.id.nav_main,
        R.id.nav_program,
        R.id.nav_schedule,
        R.id.nav_mypage
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        val idx = intent.getIntExtra(FRAGMENT_IDX_KEY, 0)
        setBottomNavigationView(idx)
    }

    override fun onResume() {
        super.onResume()

        myPageViewModel.checkSignedIn()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    private fun initScreen() {
        this.setStatusBarTransparent()
        binding.mainCl.setPadding(0, 0, 0, this.navigationHeight())
    }

    private fun setBottomNavigationView(initialIdx: Int) {
        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                fragmentIds[0] -> {
                    replaceFragment(MainFragment())
                    true
                }

                fragmentIds[1] -> {
                    replaceFragment(ProgramFragment())
                    true
                }

                fragmentIds[2] -> {
                    replaceFragment(ScheduleFragment())
                    true
                }

                fragmentIds[3] -> {
                    replaceFragment(MyPageFragment())
                    true
                }

                else -> false
            }
        }

        binding.mainBnv.selectedItemId = fragmentIds[initialIdx]
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_fl_container, fragment).commit()
    }
}