package com.codezen.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}

        setContentView(R.layout.activity_profile)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Orders::class.java))
        finish()
    }

    fun goToUpdateProfile(view: View){
        startActivity(Intent(this, UpdateProfile::class.java))
        finish()
    }
}