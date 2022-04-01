package com.codezen.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ForgetPassword : AppCompatActivity() {
    lateinit var etEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }

    fun sendPasswordResetEmail(view: View){
        etEmail = findViewById(R.id.et_Email)
        var email = etEmail.text.toString()

        if (etEmail.text.toString().equals("")) {
            etEmail.setError("Please Enter Email")
        }
        else
        {
            FirebaseAuth.getInstance().sendPasswordResetEmail("${email}").addOnCompleteListener { task -> if (task.isSuccessful) {
                val snackbar = Snackbar.make(view, "Password Reset Email Sent",
                        Snackbar.LENGTH_LONG).setAction("Action", null)
                snackbar.show()
                startActivity(Intent(this, Login::class.java))
                finish()
            } else {
                val snackbar = Snackbar.make(view, "Cannot Send Password Reset Email",
                        Snackbar.LENGTH_LONG).setAction("Action", null)
                snackbar.show()
            } }
        }
    }
}