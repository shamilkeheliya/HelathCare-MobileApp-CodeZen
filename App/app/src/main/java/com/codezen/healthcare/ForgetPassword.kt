package com.codezen.healthcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar

class ForgetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}
    }

    fun sendPasswordResetEmail(view: View){
        val snackbar = Snackbar.make(view, "Replace with your own action",
            Snackbar.LENGTH_LONG).setAction("Action", null)
        snackbar.show()
    }
}