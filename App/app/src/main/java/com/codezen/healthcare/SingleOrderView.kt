package com.codezen.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_single_order_view.*

class SingleOrderView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_order_view)

        val documentID = intent.getStringExtra("docID")
        // use this documentID to get correct Document form database


        // Example
        textView4.text = documentID
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Orders::class.java))
        finish()
    }
}