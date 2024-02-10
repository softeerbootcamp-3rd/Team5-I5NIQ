package com.hyundai.myexperience.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this

        var toolbar = binding.toolbar.toolbar
        binding.toolbar.toolBarTitle.text = "회원가입"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //supportActionBar?.apply{
        //    // 뒤로가기 버튼 활성화
        //    setDisplayHomeAsUpEnabled(true)
        //    setDisplayShowHomeEnabled(true)
        //}
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}