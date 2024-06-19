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

class Login : AppCompatActivity() {
    private lateinit var etemail :EditText
    private lateinit var etpassword :EditText
    private lateinit var btnlogin :Button
    private lateinit var btnsignup :Button
    @SuppressLint("MissingInflatedId")

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        etemail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)
        btnlogin = findViewById(R.id.btnlogin)
        btnsignup = findViewById(R.id.btnsignup)

        btnsignup.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }
        btnlogin.setOnClickListener {
            val email = etemail.text.toString()
            val password = etpassword.text.toString()

            login(email,password)
        }

    }

    private fun login(email: String, password: String) {
        //
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("main","signup successful")
                    val intent = Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("main","error occurs not found")
                    Toast.makeText(this@Login,"User not found",Toast.LENGTH_SHORT).show()
                }
            }
    }
}