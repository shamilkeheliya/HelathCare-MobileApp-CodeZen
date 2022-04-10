package com.codezen.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {


    lateinit var etName: EditText
    lateinit var etAddress: EditText
    lateinit var etPhoneNumber: EditText
    lateinit var etEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}

        setContentView(R.layout.activity_profile)

        etName = findViewById(R.id.et_name)
        etAddress = findViewById(R.id.et_address)
        etPhoneNumber = findViewById(R.id.et_phone)
        etEmail = findViewById(R.id.et_Email)

        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {
            et_name.setText(it.data!!.getValue("name").toString())
            et_address.setText(it.data!!.getValue("address").toString())
            et_phone.setText(it.data!!.getValue("mobile").toString())
            et_Email.setText(it.data!!.getValue("email").toString())

        }.addOnFailureListener{
            Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()

            startActivity(Intent(this, Orders::class.java))
            finish()
        }

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