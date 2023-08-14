package com.example.ca3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Rating : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        val btn = findViewById<Button>(R.id.submitButton)
        btn.setOnClickListener {
            Toast.makeText(this,"Thank you for Rating Us  :)",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}