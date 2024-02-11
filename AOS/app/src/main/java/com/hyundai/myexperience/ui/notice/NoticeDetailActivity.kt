package com.hyundai.myexperience.ui.notice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityNoticeDetailBinding

class NoticeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoticeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_detail)
        binding.lifecycleOwner = this

        val title = intent.getStringExtra("notice")
        binding.tvTitle.text = title
    }
}