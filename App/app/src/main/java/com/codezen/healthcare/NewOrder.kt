package com.codezen.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NewOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_order)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Orders::class.java))
        finish()
    }
}