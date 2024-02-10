package com.hyundai.myexperience.ui.program_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityProgramInfoBinding
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent

class ProgramInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgramInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()

        this.setStatusBarTransparent()
        binding.clMain.setPadding(0, 0, 0, navigationHeight())
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_program_info)
        binding.lifecycleOwner = this
    }
}