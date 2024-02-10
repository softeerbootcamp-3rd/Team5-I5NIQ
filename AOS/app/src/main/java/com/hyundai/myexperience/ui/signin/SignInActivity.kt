package com.hyundai.myexperience.ui.signin

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivitySignInBinding
import com.hyundai.myexperience.databinding.TopbarLayoutBinding
import com.hyundai.myexperience.ui.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var toolbarBinding: TopbarLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.lifecycleOwner = this

        val intent = Intent(this, SignUpActivity::class.java)
        binding.tvSignup.setOnClickListener{startActivity(intent)}

        val toolbar = binding.toolbar.toolbar
        binding.toolbar.toolBarTitle.text = "회원가입"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}