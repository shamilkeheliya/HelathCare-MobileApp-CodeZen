package com.codezen.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_update_profile.*

class UpdateProfile : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var etAddress: EditText
    lateinit var etPhoneNumber: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}

        setContentView(R.layout.activity_update_profile)

        etName = findViewById(R.id.et_name)
        etAddress = findViewById(R.id.et_address)
        etPhoneNumber = findViewById(R.id.et_phone)

        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {

            et_name.setText(it.data!!.getValue("name").toString())
            et_address.setText(it.data!!.getValue("address").toString())
            et_phone.setText(it.data!!.getValue("mobile").toString())

        }.addOnFailureListener{
            Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()

            startActivity(Intent(this, Orders::class.java))
            finish()
        }
    }

    fun validate():Boolean{
        if (etName.text.toString().equals("")) {
            etName.setError("Name Cannot be Empty")
            return false
        }else if (etAddress.text.toString().equals("")) {
            etAddress.setError("Address Cannot be Empty")
            return false
        }else if (etPhoneNumber.text.toString().equals("")) {
            etPhoneNumber.setError("Phone Number Cannot be Empty")
            return false
        }else{
          return true
        }
    }

    fun updateUserData(view: View){
        if(validate()){
            val name = etName.text.toString()
            val address = etAddress.text.toString()
            val mobile = etPhoneNumber.text.toString()

            val user = hashMapOf(
                "name" to name,
                "address" to address,
                "mobile" to mobile,
                "email" to FirebaseAuth.getInstance().currentUser!!.email
            )

            Firebase.firestore.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).set(user).addOnSuccessListener {
                Toast.makeText(applicationContext,"Updated Successful", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, Profile::class.java))
                finish()
            }.addOnFailureListener{
                Toast.makeText(applicationContext,"Cannot Update", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, Profile::class.java))
        finish()
    }
}