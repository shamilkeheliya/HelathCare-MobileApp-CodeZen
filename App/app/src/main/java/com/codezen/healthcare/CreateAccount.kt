package com.codezen.healthcare

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CreateAccount : AppCompatActivity() {

    private lateinit var  auth: FirebaseAuth


    lateinit var etFirstName: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etRepeatPassword: EditText
    lateinit var etAddress: EditText
    lateinit var etPhoneNumber: EditText
    val MIN_PASSWORD_LENGTH = 6;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        auth= FirebaseAuth.getInstance()

        viewInitializations()

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}
    }

    fun viewInitializations() {
        etFirstName = findViewById(R.id.et_first_name)
        etEmail = findViewById(R.id.et_Email)
        etPassword = findViewById(R.id.et_password)
        etRepeatPassword = findViewById(R.id.et_repeat_password)
        etAddress = findViewById(R.id.et_address)
        etPhoneNumber=findViewById(R.id.et_phone)



        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Checking if the input in form is valid
    fun validateInput(): Boolean {
        if (etFirstName.text.toString().equals("")) {
            etFirstName.setError("Please Enter First Name")
            return false
        }

        if (etEmail.text.toString().equals("")) {
            etEmail.setError("Please Enter Email")
            return false
        }
        if (etPassword.text.toString().equals("")) {
            etPassword.setError("Please Enter Password")
            return false
        }
        if (etRepeatPassword.text.toString().equals("")) {
            etRepeatPassword.setError("Please Enter Repeat Password")
            return false
        }
        if (etAddress.text.toString().equals("")) {
            etFirstName.setError("Please Enter Your Address")
            return false
        }
        if (etPhoneNumber.text.toString().equals("")) {
            etFirstName.setError("Please Enter Ypur Mobile Number")
            return false
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.text.toString())) {
            etEmail.setError("Please Enter Valid Email")
            return false
        }



        // checking minimum password Length
        if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
            etPassword.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters")
            return false
        }

        // Checking if repeat password is same
        if (!etPassword.text.toString().equals(etRepeatPassword.text.toString())) {
            etRepeatPassword.setError("Password does not match")
            return false
        }
        return true
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Hook Click Event

    fun register(view: View) {
        if (validateInput()) {

            // Input is valid, here send data to server

            val firstName = etFirstName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val repeatPassword = etRepeatPassword.text.toString()
            val address = etAddress.text.toString()
            val phone = etPhoneNumber.text.toString()

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser!!.uid

                    val user = hashMapOf(
                        "name" to firstName,
                        "email" to email,
                        "address" to address,
                        "mobile" to phone,
                    )

                    Firebase.firestore.collection("users").document(uid).set(user).addOnSuccessListener {
                        val intent = Intent(this, Orders::class.java)
                        startActivity(intent)
                        finish()
                    }

                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

    fun goToLogin(view: View) {
        startActivity(Intent(this, Login::class.java))
        finish()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, Login::class.java))
        finish()
    }
}
