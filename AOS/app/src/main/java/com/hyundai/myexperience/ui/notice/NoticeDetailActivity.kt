package com.hyundai.myexperience.ui.notice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import coil.load
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityNoticeDetailBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityNoticeDetailBinding
    private val viewModel: NoticeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        val id : Int = intent.getIntExtra("id", 30)
        viewModel.setNoticeId(id)
        viewModel.noticeDetailRequest()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_detail)
        binding.lifecycleOwner = this

        binding.noticeDetailViewModel = viewModel
    }

    private fun initScreen() {
        this.setStatusBarTransparent()
        binding.clNoticeDetail.setPadding(0, 0, 0, this.navigationHeight())

        setToolbar(binding.toolbarLayout.toolbar, binding.toolbarLayout.toolBarTitle, "")
    }
}