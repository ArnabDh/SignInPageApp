package com.example.second_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name=intent.getStringExtra(signin.key1)
        val phone=intent.getStringExtra(signin.key2)

        val welcomeText=findViewById<TextView>(R.id.textname)
        val phn=findViewById<TextView>(R.id.textphone)
        welcomeText.text= "Welcome $name"
        phn.text="Phone number : $phone"
    }
}