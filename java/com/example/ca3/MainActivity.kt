package com.example.ca3

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn = findViewById<ImageButton>(R.id.options)
        btn.setOnClickListener {
            showOptionsMenu(it)
        }
    }

    fun InteractFun(view: View){
        intent = Intent(this,Interact::class.java)
        startActivity(intent)
    }

    private fun showOptionsMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.option, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.rate -> {
                    intent = Intent(this,Rating::class.java)
                    startActivity(intent)
                    true
                }
                R.id.logout -> {
                    val sharedPref = getSharedPreferences("saved", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    startActivity(Intent(this, Register::class.java))
                    finish()
                    true
                }
                R.id.Exit -> {
                    finish()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}