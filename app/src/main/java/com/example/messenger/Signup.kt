package com.example.messenger

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signup : AppCompatActivity() {
    private lateinit var etname : EditText
    private lateinit var etemail : EditText
    private lateinit var etpassword : EditText
    private lateinit var btnsignup : Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef : DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {


        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        etname = findViewById(R.id.etname)
        etemail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)
        btnsignup = findViewById(R.id.btnsignup)
        super.onCreate(savedInstanceState)

        btnsignup.setOnClickListener {
            val name = etname.text.toString()
            val email = etemail.text.toString()
            val password = etpassword.text.toString()
            Log.d("main","signup initial")
            signup(name,email,password)
        }
    }

    private fun signup(name : String,email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for this page to home
                    Log.d("main","signup successful")
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent = Intent(this@Signup,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Log.d("main","some error",)
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String?) {
        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid!!).setValue(user(name,email,uid))
    }
}