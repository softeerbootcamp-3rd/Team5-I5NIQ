package com.hyundai.myexperience.ui.program_category

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityProgramCategoryBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.program_info.ProgramInfoActivity
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent

class ProgramCategoryActivity : BaseActivity() {
    private lateinit var binding: ActivityProgramCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        binding.tvLevel1.setOnClickListener {
            onClickLevel()
        }

        binding.tvLevel2.setOnClickListener {
            onClickLevel()
        }

        binding.tvLevel3.setOnClickListener {
            onClickLevel()
        }

        binding.tvLevelNAdvanced.setOnClickListener {
            onClickLevel()
        }

        binding.tvLevelNMasters.setOnClickListener {
            onClickLevel()
        }

        binding.tvOffRoad.setOnClickListener {
            onClickLevel()
        }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_program_category)
        binding.lifecycleOwner = this
    }

    private fun initScreen() {
        this.setStatusBarTransparent()
        binding.tvOffRoad.setPadding(0, 0, 0, navigationHeight())

        setToolbar(binding.toolbarLayout.toolbar, binding.toolbarLayout.toolBarTitle, "")
    }

    private fun onClickLevel() {
        val intent = Intent(this, ProgramInfoActivity::class.java)
        startActivity(intent)
    }
}