package com.codezen.healthcare

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_single_order_view.*
import kotlinx.android.synthetic.main.activity_update_profile.*

class SingleOrderView : AppCompatActivity() {

    lateinit var documentID : String
    lateinit var orderStatus : String
    lateinit var decription : String
    lateinit var prescriptionURL : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}

        setContentView(R.layout.activity_single_order_view)

        documentID = intent.getStringExtra("docID").toString()

        FirebaseFirestore.getInstance().collection("orders").document(documentID.toString()).get().addOnSuccessListener {

            orderStatus = it.data!!.getValue("status").toString()
            decription = it.data!!.getValue("description").toString()
            prescriptionURL = it.data!!.getValue("prescription").toString()

            txt_date.text = it.data!!.getValue("date").toString()
            txt_time.text = it.data!!.getValue("time").toString()
            txt_status.text = it.data!!.getValue("status").toString()
            txt_description.text = it.data!!.getValue("description").toString()
            txt_amount.text = it.data!!.getValue("amount").toString()

            checkStatus()
            descriptionVisibility()

            DownloadImageFromInternet(findViewById(R.id.pescriptionImage)).execute(prescriptionURL)

        }.addOnFailureListener{
            Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("StaticFieldLeak")
    @Suppress("DEPRECATION")
    private inner class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        init {
            Toast.makeText(applicationContext, "Prescription Loading...", Toast.LENGTH_SHORT).show()
        }
        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageURL = urls[0]
            var image: Bitmap? = null
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
            }
            catch (e: Exception) {
                Toast.makeText(applicationContext, "Cannot load the Prescription", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }

    fun checkStatus(){
        if(orderStatus == "Pending"){
            txt_status.setTextColor(Color.parseColor("#dbba00"))
            lbl_amount.setVisibility(View.GONE)
            lbl_rs.setVisibility(View.GONE)
            txt_amount.setVisibility(View.GONE)
        }
        else if(orderStatus == "Packing"){
            txt_status.setTextColor(Color.parseColor("#ffaa00"))
            buttonDeleteOrder.setVisibility(View.GONE)
        }
        else if(orderStatus == "Delivering"){
            txt_status.setTextColor(Color.parseColor("#0091ff"))
            buttonDeleteOrder.setVisibility(View.GONE)
        }
        else if(orderStatus == "Done"){
            txt_status.setTextColor(Color.parseColor("#00ff00"))
            buttonDeleteOrder.setVisibility(View.GONE)
        }
        else {
            txt_status.setTextColor(Color.parseColor("#ff0000"))
            buttonDeleteOrder.setVisibility(View.GONE)
            lbl_amount.setVisibility(View.GONE)
            lbl_rs.setVisibility(View.GONE)
            txt_amount.setVisibility(View.GONE)
        }
    }

    fun descriptionVisibility(){
        if(decription == "" || decription == null){
            txt_description.setVisibility(View.GONE)
            lbl_description.setVisibility(View.GONE)
        }
    }

    fun deleteOrder(view: View){
        FirebaseFirestore.getInstance().collection("orders").document(documentID).delete().addOnSuccessListener {
            Toast.makeText(applicationContext,"Order Deleted Successful", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, Orders::class.java))
            finish()
        }.addOnFailureListener{
            Toast.makeText(applicationContext,"Cannot Delete the Order", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Orders::class.java))
        finish()
    }
}
