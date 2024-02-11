package com.hyundai.myexperience.ui.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivitySignInBinding
import com.hyundai.myexperience.ui.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.lifecycleOwner = this

        val intent = Intent(this, SignUpActivity::class.java)
        binding.tvSignup.setOnClickListener{startActivity(intent)}

        val toolbarLayout = binding.toolbarLayout
        toolbarLayout.toolBarTitle.text = ""
        setSupportActionBar(toolbarLayout.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
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