package com.codezen.healthcare

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.protobuf.Empty
import com.google.type.DateTime
import kotlinx.android.synthetic.main.activity_new_order.*
import kotlinx.coroutines.awaitAll
import java.net.URI
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.hashMapOf as hashMapOf

class NewOrder : AppCompatActivity() {

    lateinit var imageURI : Uri
    lateinit var textDescription: EditText
    lateinit var prescriptionURL : String
    var hasImage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}

        setContentView(R.layout.activity_new_order)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Orders::class.java))
        finish()
    }

    fun selectImage(view: View){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK){
            imageURI = data?.data!!
            pescriptionImage.setImageURI(imageURI)
            hasImage = true
        }
    }

    fun placeOrder(view: View){
        if(!hasImage){
            Toast.makeText(applicationContext,"Add Prescription Image", Toast.LENGTH_LONG).show()
        }else{
            textDescription = findViewById(R.id.editTextDescription)

            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Placing Your Order...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
            val now = Date()
            val fileName = formatter.format(now)
            val storageReference = FirebaseStorage.getInstance().getReference("prescriptions/$fileName")

            storageReference.putFile(imageURI).addOnSuccessListener {

                storageReference.downloadUrl.addOnSuccessListener {
                    prescriptionURL = it.toString()

                    val order = hashMapOf(
                            "customer" to FirebaseAuth.getInstance().currentUser?.uid,
                            "prescription" to prescriptionURL,
                            "description" to textDescription.text.toString(),
                            "datetime" to Timestamp.now(),
                            "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${Calendar.getInstance().get(Calendar.MONTH)}-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",
                            "time" to "${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}:${Calendar.getInstance().get(Calendar.MINUTE)}",
                            "status" to "Pending",
                            "amount" to "",
                            "paid" to false,
                    )

                    Firebase.firestore.collection("orders").add(order).addOnSuccessListener {
                        if(progressDialog.isShowing) progressDialog.dismiss()
                        Toast.makeText(applicationContext,"Successfully Placed Order", Toast.LENGTH_LONG).show()

                        startActivity(Intent(this, Orders::class.java))
                        finish()
                    }.addOnFailureListener{
                        if(progressDialog.isShowing) progressDialog.dismiss()
                        Toast.makeText(applicationContext,"Failed to Place Order", Toast.LENGTH_LONG).show()
                    }
                }
            }.addOnFailureListener{
                if(progressDialog.isShowing) progressDialog.dismiss()
                Toast.makeText(applicationContext,"Failed to Place Order", Toast.LENGTH_LONG).show()
            }
        }
    }
}