package com.codezen.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

private val SPLASH_TIME_OUT:Long = 3000
class LoadingScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)

        Handler().postDelayed({

            startActivity(Intent(this,Home::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}