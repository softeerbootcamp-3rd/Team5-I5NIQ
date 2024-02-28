package com.hyundai.myexperience.ui.notice

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.NoticesItem
import com.hyundai.myexperience.databinding.ActivityNoticeListBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.notice.adapter.NoticesAdapter
import com.hyundai.myexperience.ui.notice.adapter.NoticesItemClickListener
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeListActivity : BaseActivity() {
    private val viewModel: NoticeListViewModel by viewModels()
    private lateinit var binding: ActivityNoticeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()
        initNoticeList()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_list)
        binding.lifecycleOwner = this
    }

    private fun initScreen() {
        this.setStatusBarTransparent()
        binding.clNoticeList.setPadding(0, 0, 0, this.navigationHeight())

        setToolbar(binding.toolbarLayout.toolbar, binding.toolbarLayout.toolBarTitle, "공지사항")
    }

    private fun initNoticeList() {
        val onItemClickListener: NoticesItemClickListener = object :
            NoticesItemClickListener {
            override fun onItemClick(notice: NoticesItem) {
                viewModel.openNoticeDetails(notice)
            }
        }

        val adapter = NoticesAdapter(viewModel.notices.value!!, onItemClickListener)
        viewModel.noticeRequest()

        binding.rvNotice.adapter = adapter
        binding.rvNotice.layoutManager = LinearLayoutManager(this)
        viewModel.notices.observe(this) {
            adapter.setData(it)
        }

        viewModel.notice.observe(this) { notice ->
            notice?.let {
                val intent = Intent(this, NoticeDetailActivity::class.java)
                intent.putExtra("id", notice.noticeId)
                startActivity(intent)
            }
        }
    }
}