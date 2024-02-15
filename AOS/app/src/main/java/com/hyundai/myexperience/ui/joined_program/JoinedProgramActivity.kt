package com.hyundai.myexperience.ui.joined_program

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivityJoinedProgramBinding
import com.hyundai.myexperience.ui.joined_program.adapter.ProgramsAdapter
import com.hyundai.myexperience.utils.VerticalSpaceDecoration

class JoinedProgramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJoinedProgramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()

        val toolbarLayout = binding.toolbarLayout
        toolbarLayout.toolBarTitle.text = "참여 예정 프로그램"
        setSupportActionBar(toolbarLayout.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        var programsList = listOf(
            ProgramsItem("24년 2월 3일 오후 3시", "제네시스 드라이빙 익스피리언스 Level2"),
            ProgramsItem("24년 2월 2일 오후 3시", "현대 드라이빙 익스피리언스 Level2"),
            ProgramsItem("24년 2월 1일 오후 3시", "현대 드라이빙 익스피리언스 Level1", true)
        )

        val adapter = ProgramsAdapter(programsList)
        binding.rvJoinedPrograms.adapter = adapter
        binding.rvJoinedPrograms.addItemDecoration(VerticalSpaceDecoration(8))
        binding.rvJoinedPrograms.layoutManager = LinearLayoutManager(this)
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_joined_program)
        binding.lifecycleOwner = this
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