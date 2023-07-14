package com.example.second_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signin : AppCompatActivity() {
    lateinit var database: DatabaseReference
    companion object{
        const val key1="com.example.second_project.signin.name"
        const val key2="com.example.second_project.signin.phone"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val sign=findViewById<Button>(R.id.btnSignIn)
        val name=findViewById<TextInputEditText>(R.id.signName)

        sign.setOnClickListener {
            val name1=name.text.toString()
            if(name1.isNotEmpty()){
                readData(name1)

            }else{
                Toast.makeText(this,"Please Enter your name",Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun readData(name: String) {
        database=FirebaseDatabase.getInstance().getReference("users")
        database.child(name).get().addOnSuccessListener {
            if(it.exists()){
                //new page
                val dname=it.child("name").value
                val phone=it.child("phone").value
                val welcome=Intent(this,Welcome::class.java)
                welcome.putExtra(key1,dname.toString())
                welcome.putExtra(key2,phone.toString())
                startActivity(welcome)
            }else{
                Toast.makeText(this,"user doesn't exist",Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }

    }
}