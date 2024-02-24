package com.hyundai.myexperience.ui.joined_program

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramItem
import com.hyundai.myexperience.databinding.ActivityJoinedProgramBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.common.BasicAlertDialog
import com.hyundai.myexperience.ui.joined_program.adapter.ProgramsAdapter
import com.hyundai.myexperience.ui.joined_program.adapter.ProgramsItemClickListener
import com.hyundai.myexperience.utils.VerticalSpaceDecoration
import com.hyundai.myexperience.utils.dpToPx
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinedProgramActivity : BaseActivity() {
    private lateinit var binding: ActivityJoinedProgramBinding
    private val viewModel: JoinedProgramViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()
        initJoinedProgramList()


    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_joined_program)
        binding.lifecycleOwner = this
    }

    private fun initScreen() {
        this.setStatusBarTransparent()
        binding.clJoinedProgram.setPadding(0, 0, 0, this.navigationHeight())

        val title = intent.getStringExtra("title")!!
        setToolbar(binding.toolbarLayout.toolbar, binding.toolbarLayout.toolBarTitle, title)
    }

    private fun initJoinedProgramList() {
        //// 클릭리스너 -> 다이얼로그
        val onItemClickListener: ProgramsItemClickListener = object :
            ProgramsItemClickListener {
            override fun onItemClick(program: JoinedProgramItem) {
                openDialog()
            }
        }

        val adapter = ProgramsAdapter(viewModel.joinedPrograms.value!!, onItemClickListener)
        viewModel.joinedProgramRequest()

        binding.rvJoinedPrograms.adapter = adapter
        binding.rvJoinedPrograms.addItemDecoration(VerticalSpaceDecoration(this.dpToPx(10)))
        binding.rvJoinedPrograms.layoutManager = LinearLayoutManager(this)
        viewModel.joinedPrograms.observe(this) {
            adapter.setData(it)
        }
    }

    private fun openDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("알림")
            .setMessage("이것은 기본 알림 대화 상자입니다.")
            .setPositiveButton("확인") { dialog, which ->
                dialog.dismiss()
            }

        val detailDialog: AlertDialog = builder.create()
        detailDialog.setOnShowListener {
            detailDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(resources.getColor(R.color.red))
        }

        detailDialog.show()
    }

}
