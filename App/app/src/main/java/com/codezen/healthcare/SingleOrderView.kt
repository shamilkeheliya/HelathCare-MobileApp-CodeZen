package com.codezen.healthcare

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_single_order_view.*
import kotlinx.android.synthetic.main.activity_update_profile.*

class SingleOrderView : AppCompatActivity() {

    lateinit var orderStatus : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}

        setContentView(R.layout.activity_single_order_view)

        val documentID = intent.getStringExtra("docID")

        FirebaseFirestore.getInstance().collection("orders").document(documentID.toString()).get().addOnSuccessListener {

            orderStatus = it.data!!.getValue("status").toString()
            txt_date.text = it.data!!.getValue("date").toString()
            txt_time.text = it.data!!.getValue("time").toString()
            txt_status.text = it.data!!.getValue("status").toString()
            txt_description.text = it.data!!.getValue("description").toString()
            changeColor()

        }.addOnFailureListener{
            Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()
        }
    }

    fun changeColor(){
        if(orderStatus == "Pending"){
            txt_status.setTextColor(Color.parseColor("#dbba00"))
        }
        else if(orderStatus == "Packing"){
            txt_status.setTextColor(Color.parseColor("#ffaa00"))
        }
        else if(orderStatus == "Delivering"){
            txt_status.setTextColor(Color.parseColor("#0091ff"))
        }
        else if(orderStatus == "Done"){
            txt_status.setTextColor(Color.parseColor("#00ff00"))
        }
        else {
            txt_status.setTextColor(Color.parseColor("#ff0000"))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Orders::class.java))
        finish()
    }
}
