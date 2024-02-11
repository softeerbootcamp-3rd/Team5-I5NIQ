package com.hyundai.myexperience.ui.program_category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityProgramCategoryBinding
import com.hyundai.myexperience.ui.program_info.ProgramInfoActivity
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent

class ProgramCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgramCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()

        this.setStatusBarTransparent()
        binding.tvOffRoad.setPadding(0, 0, 0, navigationHeight())

        binding.tvLevel1.setOnClickListener {
            val intent = Intent(this, ProgramInfoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_program_category)
        binding.lifecycleOwner = this
    }
}