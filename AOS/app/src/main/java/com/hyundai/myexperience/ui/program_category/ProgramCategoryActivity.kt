package com.hyundai.myexperience.ui.program_category

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.COMPANY_GENESIS
import com.hyundai.myexperience.COMPANY_HMG
import com.hyundai.myexperience.COMPANY_HYUNDAI
import com.hyundai.myexperience.COMPANY_KIA
import com.hyundai.myexperience.COMPANY_TYPE_KEY
import com.hyundai.myexperience.PROGRAM_LEVEL_1
import com.hyundai.myexperience.PROGRAM_LEVEL_2
import com.hyundai.myexperience.PROGRAM_LEVEL_3
import com.hyundai.myexperience.PROGRAM_LEVEL_N_ADVANCED
import com.hyundai.myexperience.PROGRAM_LEVEL_N_MASTERS
import com.hyundai.myexperience.PROGRAM_OFF_ROAD
import com.hyundai.myexperience.PROGRAM_LEVEL_KEY
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityProgramCategoryBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.program_info.ProgramInfoActivity
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent

class ProgramCategoryActivity : BaseActivity() {
    private lateinit var binding: ActivityProgramCategoryBinding
    private var company = COMPANY_HYUNDAI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        binding.actvDropdown.setOnItemClickListener { _, _, position, _ ->
            onClickDropDownItem(position)
        }

        binding.tvLevel1.setOnClickListener {
            onClickLevel(PROGRAM_LEVEL_1)
        }

        binding.tvLevel2.setOnClickListener {
            onClickLevel(PROGRAM_LEVEL_2)
        }

        binding.tvLevel3.setOnClickListener {
            onClickLevel(PROGRAM_LEVEL_3)
        }

        binding.tvLevelNAdvanced.setOnClickListener {
            onClickLevel(PROGRAM_LEVEL_N_ADVANCED)
        }

        binding.tvLevelNMasters.setOnClickListener {
            onClickLevel(PROGRAM_LEVEL_N_MASTERS)
        }

        binding.tvOffRoad.setOnClickListener {
            onClickLevel(PROGRAM_OFF_ROAD)
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

    private fun onClickDropDownItem(position: Int) {
        company = when(position) {
            0 -> COMPANY_HYUNDAI
            1 -> COMPANY_KIA
            2 -> COMPANY_GENESIS
            else -> COMPANY_HMG
        }

        val image = when (company) {
            COMPANY_HYUNDAI -> R.drawable.program_category_iv_hyundai
            COMPANY_KIA -> R.drawable.program_category_iv_kia
            COMPANY_GENESIS -> R.drawable.program_category_iv_genesis
            else -> R.drawable.program_category_iv_hmg
        }
        binding.ivBackground.setImageResource(image)

        val text = when (company) {
            COMPANY_HYUNDAI -> resources.getString(R.string.program_category_explain_hyundai)
            COMPANY_KIA -> resources.getString(R.string.program_category_explain_kia)
            COMPANY_GENESIS -> resources.getString(R.string.program_category_explain_genesis)
            else -> resources.getString(R.string.program_category_explain_hmg)
        }
        binding.tvExplain.text = text
    }

    private fun onClickLevel(level: String) {
        val intent = Intent(this, ProgramInfoActivity::class.java)
        intent.putExtra(PROGRAM_LEVEL_KEY, level)
        intent.putExtra(COMPANY_TYPE_KEY, company)
        startActivity(intent)
    }
}