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
import com.hyundai.myexperience.data.entity.NoticesItem
import com.hyundai.myexperience.databinding.ActivityNoticeListBinding
import com.hyundai.myexperience.ui.notice.adapter.NoticesAdapter
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeListActivity : AppCompatActivity() {
    private val viewModel: NoticeListViewModel by viewModels()
    private lateinit var binding: ActivityNoticeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_list)
        binding.lifecycleOwner = this

        this.setStatusBarTransparent()
        binding.noticeCl.setPadding(0, 0, 0, this.navigationHeight())

        val toolbarLayout = binding.toolbarLayout
        toolbarLayout.toolBarTitle.text = "공지사항"
        setSupportActionBar(toolbarLayout.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val adapter = NoticesAdapter(viewModel, viewModel.notices.value!!)
        viewModel.getResponse()

        binding.rvNotice.adapter = adapter
        binding.rvNotice.layoutManager = LinearLayoutManager(this)

        viewModel.notices.observe(this, {
            adapter.setData(it)
        })

        viewModel.notice.observe(this, Observer { notice ->
            notice?.let {
                val intent = Intent(this, NoticeDetailActivity::class.java)
                intent.putExtra("title", notice.noticeTitle)
                intent.putExtra("date", notice.date)
                intent.putExtra("detail", notice.noticeDetail)
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