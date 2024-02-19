package com.hyundai.myexperience.ui.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivitySignUpBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent

class SignUpActivity : BaseActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        binding.etSignupId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.isEmpty()) {
                    binding.tilSignupId.error = "이미 등록된 아이디입니다."
                } else {
                    binding.tilSignupId.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this
    }

    private fun initScreen() {
        this.setStatusBarTransparent()
        binding.cl.setPadding(0, 0, 0, this.navigationHeight())

        setToolbar(
            binding.toolbarLayout.toolbar,
            binding.toolbarLayout.toolBarTitle,
            resources.getString(R.string.signup_btn)
        )
    }

}