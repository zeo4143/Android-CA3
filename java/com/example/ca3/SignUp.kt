package com.example.ca3

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.IOException

class SignUp : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var pass: EditText
    lateinit var cpass: EditText
    lateinit var submit: Button
    lateinit var data1 : String
    lateinit var data2 : String
    lateinit var data3 : String
    var file = "key.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        name = findViewById(R.id.name)
        pass = findViewById(R.id.password)
        cpass = findViewById(R.id.confirmPassword)
        submit = findViewById(R.id.button)

        submit.setOnClickListener {
            data1 = name.text.toString()
            data2 = pass.text.toString()
            data3 = cpass.text.toString()

            if(data2 != data3){
                Toast.makeText(this,"Password doesn't match !!",Toast.LENGTH_LONG).show()
            }
            if(data1.isEmpty() || data2.isEmpty() || data3.isEmpty()){
                Toast.makeText(this, "Fill all the details !!",Toast.LENGTH_LONG).show()
            }

            try {
                val fout=openFileOutput(file, MODE_APPEND)
                data1 += " ";
                data2 += "\n";
                fout.write(data1.toByteArray())
                fout.write(data2.toByteArray())
                fout.close()
                Toast.makeText(this,"Goto Sign In",Toast.LENGTH_LONG).show()
            }
            catch (e: IOException){
                e.printStackTrace()
            }
        }

    }
}