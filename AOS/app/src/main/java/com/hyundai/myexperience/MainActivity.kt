package com.hyundai.myexperience

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hyundai.myexperience.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        setBottomNavigationView()

        if (savedInstanceState == null) {
            binding.mainBnv.selectedItemId = R.id.nav_main
        }

        this.setStatusBarTransparent()
        binding.mainCl.setPadding(0, 0, 0, this.navigationHeight())
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
}