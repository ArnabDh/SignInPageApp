package com.example.second_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.android.material.button.MaterialButton

class signup : AppCompatActivity() {
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signupbutton=findViewById<Button>(R.id.btnSignUp)
        val name=findViewById<TextInputEditText>(R.id.name)
        val phone=findViewById<TextInputEditText>(R.id.phone)
        val password=findViewById<TextInputEditText>(R.id.password)
        val signin=findViewById<Button>(R.id.signIn)

        signupbutton.setOnClickListener {
            val name1=name.text.toString()
            val phone1=phone.text.toString()
            val password1=password.text.toString()
            val user=User(name1,phone1,password1)

            database=FirebaseDatabase.getInstance().getReference("users")
            database.child(name1).setValue(user).addOnSuccessListener {
                name.text?.clear()
                phone.text?.clear()
                password.text?.clear()

                Toast.makeText(this,"Registration Successful",Toast.LENGTH_SHORT).show()
                val intentwelcome=Intent(this,signin::class.java)
                startActivity(intentwelcome)
            }.addOnFailureListener {
                Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show()
            }
        }


        signin.setOnClickListener {
            val intentsign=Intent(this,signin::class.java)
            startActivity(intentsign)
        }
    }
}
