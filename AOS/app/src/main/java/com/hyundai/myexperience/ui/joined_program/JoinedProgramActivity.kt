package com.hyundai.myexperience.ui.joined_program

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.DETAIL_DIALOG_TAG
import com.hyundai.myexperience.JOINED_STATUS
import com.hyundai.myexperience.JOINED_TYPE_KEY
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.user.JoinedProgramItem
import com.hyundai.myexperience.databinding.ActivityJoinedProgramBinding
import com.hyundai.myexperience.ui.common.BaseActivity
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

        val title = intent.getStringExtra(JOINED_TYPE_KEY)!!
        setToolbar(binding.toolbarLayout.toolbar, binding.toolbarLayout.toolBarTitle, title)
    }

    private fun initJoinedProgramList() {
        val onItemClickListener: ProgramsItemClickListener = object :
            ProgramsItemClickListener {
            override fun onItemClick(program: JoinedProgramItem) {
                val dialog = JoinedProgramDetailDialog(program.participationId)
                dialog.show(supportFragmentManager, DETAIL_DIALOG_TAG)
            }
        }

        val adapter = ProgramsAdapter(viewModel.joinedPrograms.value!!, onItemClickListener)

        val status = intent.getStringExtra(JOINED_STATUS)!!
        viewModel.joinedProgramRequest(status)

        binding.rvJoinedPrograms.adapter = adapter
        binding.rvJoinedPrograms.addItemDecoration(VerticalSpaceDecoration(this.dpToPx(10)))
        binding.rvJoinedPrograms.layoutManager = LinearLayoutManager(this)
        viewModel.joinedPrograms.observe(this) {
            if (it != null) {
                adapter.setData(it)
            }
        }
    }

}
