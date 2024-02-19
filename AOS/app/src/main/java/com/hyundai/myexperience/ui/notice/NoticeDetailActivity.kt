package com.hyundai.myexperience.ui.notice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityNoticeDetailBinding
import com.hyundai.myexperience.utils.setStatusBarTransparent

class NoticeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoticeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_detail)
        binding.lifecycleOwner = this

        this.setStatusBarTransparent()

        val toolbarLayout = binding.toolbarLayout
        toolbarLayout.toolBarTitle.text = ""
        setSupportActionBar(toolbarLayout.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val title = intent.getStringExtra("title")
        val date = intent.getStringExtra("date")
        val detail = intent.getStringExtra("detail")
        binding.tvNoticeTitle.text = title
        binding.tvNoticeDate.text = date
        binding.tvNoticeDetail.text = detail
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}