package com.codezen.healthcare

import android.annotation.SuppressLint
import android.app.Activity
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
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.activity_single_order_view.*
import kotlinx.android.synthetic.main.activity_update_profile.*
import java.math.BigDecimal
import java.text.NumberFormat

class SingleOrderView : AppCompatActivity() {

    lateinit var documentID : String
    var orderStatus = "Loading..."
    lateinit var decription : String
    lateinit var prescriptionURL : String
    var payment = false
    var config:PayPalConfiguration? = null
    var client_id: String = "ARKL7p7RWGWudGaKC4KIjPhAd46IzO8Jl61jfgiEIMKxO3-JirB0te6vR-v_QFk9mYz1bAq00AJ9rqze"


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
            payment = it.data!!.getValue("paid") as Boolean

            txt_date.text = it.data!!.getValue("date").toString()
            txt_time.text = it.data!!.getValue("time").toString()
            txt_status.text = it.data!!.getValue("status").toString()
            txt_description.text = it.data!!.getValue("description").toString()
            txt_amount.text = it.data!!.getValue("amount").toString()

            updatePaymentMethod()
            checkStatus()
            descriptionVisibility()

            DownloadImageFromInternet(findViewById(R.id.pescriptionImage)).execute(prescriptionURL)

        }.addOnFailureListener{
            Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()
        }


    }

    override fun onDestroy() {
        stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
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
            layoutPaymentMethord.setVisibility(View.GONE)
            layoutAboutPayment.setVisibility(View.GONE)
            lbl_paymentMethod.setVisibility(View.GONE)
        }
        else if(orderStatus == "Packing"){
            txt_status.setTextColor(Color.parseColor("#ffaa00"))
            buttonDeleteOrder.setVisibility(View.GONE)

            if(payment){
                layoutPaymentMethord.setVisibility(View.GONE)
            }else{
                layoutAboutPayment.setVisibility(View.GONE)
            }
        }
        else if(orderStatus == "Delivering"){
            txt_status.setTextColor(Color.parseColor("#0091ff"))
            buttonDeleteOrder.setVisibility(View.GONE)
            layoutPaymentMethord.setVisibility(View.GONE)
        }
        else if(orderStatus == "Done"){
            txt_status.setTextColor(Color.parseColor("#00ff00"))
            buttonDeleteOrder.setVisibility(View.GONE)
            layoutPaymentMethord.setVisibility(View.GONE)
        }
        else {
            txt_status.setTextColor(Color.parseColor("#ff0000"))
            buttonDeleteOrder.setVisibility(View.GONE)
            lbl_amount.setVisibility(View.GONE)
            lbl_rs.setVisibility(View.GONE)
            txt_amount.setVisibility(View.GONE)
            layoutPaymentMethord.setVisibility(View.GONE)
            layoutAboutPayment.setVisibility(View.GONE)
            lbl_paymentMethod.setVisibility(View.GONE)
        }
    }

    fun updatePaymentMethod(){
        if(payment){
            txt_aboutPayment.text = "Your payment done with PayPal"
        }else{
            txt_aboutPayment.text = "Your method of payment: Cash on Delivery"
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

    fun payWithPayPal(view: View){
        config = PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(client_id)
        var i = Intent(this,PayPalService::class.java)
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        startService(i)
        var amount = txt_amount.text.toString().toDouble()
        var paypal = PayPalPayment(BigDecimal.valueOf(amount), "USD", "Health Care Pharmacy", PayPalPayment.PAYMENT_INTENT_SALE)
        var intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, paypal)
        startActivityForResult(intent, 123)
        FirebaseFirestore.getInstance().collection("orders").document(documentID).update("paid", true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Orders::class.java))
        finish()
    }
}
