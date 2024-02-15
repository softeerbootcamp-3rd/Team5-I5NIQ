package com.hyundai.myexperience.ui.notice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityNoticeListBinding
import com.hyundai.myexperience.ui.notice.adapter.NoticesAdapter
import com.hyundai.myexperience.utils.setStatusBarTransparent

class NoticeListActivity : AppCompatActivity() {
    private val viewModel: NoticeListViewModel by viewModels()
    private lateinit var binding: ActivityNoticeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_list)
        binding.lifecycleOwner = this

        this.setStatusBarTransparent()

        val toolbarLayout = binding.toolbarLayout
        toolbarLayout.toolBarTitle.text = "공지사항"
        setSupportActionBar(toolbarLayout.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val noticeList = listOf(
            NoticesItem("2024년 1월 1일", "HMG 드라이빙 익스피리언스 2024년 준비 기간 안내"),
            NoticesItem("2023년 10월 10일", "2023년 HMG 드라이빙 익스피리언스 11월 운영 공지"),
            NoticesItem("2023년 9월 10일", "2023년 HMG 드라이빙 익스피리언스 10월 운영 공지"),
            NoticesItem("2023년 8월 10일", "2023년 HMG 드라이빙 익스피리언스 9월 운영 공지"),
            NoticesItem("2023년 7월 10일", "2023년 HMG 드라이빙 익스피리언스 8월 운영 공지"),
            NoticesItem("2023년 6월 10일", "2023년 HMG 드라이빙 익스피리언스 7월 운영 공지"),
            NoticesItem("2023년 5월 10일", "2023년 HMG 드라이빙 익스피리언스 6월 운영 공지"),
            NoticesItem("2023년 4월 10일", "2023년 HMG 드라이빙 익스피리언스 5월 운영 공지"),
            NoticesItem("2023년 3월 10일", "2023년 HMG 드라이빙 익스피리언스 4월 운영 공지")
        )

        val adapter = NoticesAdapter(viewModel, noticeList)
        binding.rvNotice.adapter = adapter
        binding.rvNotice.layoutManager = LinearLayoutManager(this)

        viewModel.notice.observe(this, Observer { notice ->
            notice?.let {
                val intent = Intent(this, NoticeDetailActivity::class.java)
                intent.putExtra("title", notice.noticeTitle)
                intent.putExtra("date", notice.date)
                startActivity(intent)
            }
        })
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