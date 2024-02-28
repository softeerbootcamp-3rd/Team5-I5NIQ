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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        setBottomNavigationView()

        myPageViewModel.checkSignedIn()

        if (savedInstanceState == null) {
            binding.mainBnv.selectedItemId = R.id.nav_main
        }

        val fragments = listOf(
            R.id.nav_main,
            R.id.nav_program,
            R.id.nav_schedule,
            R.id.nav_mypage
        )
        val idx = intent.getIntExtra(FRAGMENT_IDX_KEY, 0)
        binding.mainBnv.selectedItemId = fragments[idx]

        initScreen()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    private fun setBottomNavigationView() {
        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_main -> {
                    setFragment(MainFragment())
                    true
                }

                R.id.nav_program -> {
                    setFragment(ProgramFragment())
                    true
                }

                R.id.nav_schedule -> {
                    setFragment(ScheduleFragment())
                    true
                }

                R.id.nav_mypage -> {
                    setFragment(MyPageFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_fl_container, fragment).commit()
    }

    private fun initScreen() {
        this.setStatusBarTransparent()
        binding.mainCl.setPadding(0, 0, 0, this.navigationHeight())
    }

}