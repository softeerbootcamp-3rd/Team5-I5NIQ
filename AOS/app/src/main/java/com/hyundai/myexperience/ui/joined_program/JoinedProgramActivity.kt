package com.hyundai.myexperience.ui.joined_program

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityJoinedProgramBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.joined_program.adapter.ProgramsAdapter
import com.hyundai.myexperience.utils.VerticalSpaceDecoration

class JoinedProgramActivity : BaseActivity() {
    private lateinit var binding: ActivityJoinedProgramBinding
    private lateinit var programsList: List<ProgramsItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()

        val title = intent.getStringExtra("title")!!
        setToolbar(binding.toolbarLayout.toolbar, binding.toolbarLayout.toolBarTitle, title)

        programsList = listOf(
            ProgramsItem("24년 2월 3일 오후 3시", "제네시스 드라이빙 익스피리언스 Level2"),
            ProgramsItem("24년 2월 2일 오후 3시", "현대 드라이빙 익스피리언스 Level2"),
            ProgramsItem("24년 2월 1일 오후 3시", "현대 드라이빙 익스피리언스 Level1", true)
        )

        initRecyclerView()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_joined_program)
        binding.lifecycleOwner = this
    }

    private fun initRecyclerView() {
        val adapter = ProgramsAdapter(programsList)
        binding.rvJoinedPrograms.adapter = adapter
        binding.rvJoinedPrograms.addItemDecoration(VerticalSpaceDecoration(8))
        binding.rvJoinedPrograms.layoutManager = LinearLayoutManager(this)
    }

}