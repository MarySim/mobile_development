package com.example.attributes

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + 16, systemBars.top + 16, systemBars.right + 16, systemBars.bottom + 16)
            insets
        }

        val editText = findViewById<EditText>(R.id.edit_text)

        val btnBlackText = findViewById<Button>(R.id.btn_black_text)
        val btnRedText = findViewById<Button>(R.id.btn_red_text)
        val btnSize8 = findViewById<Button>(R.id.btn_size_8)
        val btnSize24 = findViewById<Button>(R.id.btn_size_24)
        val btnWhiteBg = findViewById<Button>(R.id.btn_white_bg)
        val btnYellowBg = findViewById<Button>(R.id.btn_yellow_bg)

        btnBlackText.setOnClickListener {
            editText.setTextColor(Color.BLACK)
        }
        btnRedText.setOnClickListener {
            editText.setTextColor(Color.RED)
        }

        btnSize8.setOnClickListener {
            editText.textSize = 8f
        }
        btnSize24.setOnClickListener {
            editText.textSize = 24f
        }

        btnWhiteBg.setOnClickListener {
            editText.setBackgroundColor(Color.WHITE)
        }
        btnYellowBg.setOnClickListener {
            editText.setBackgroundColor(Color.YELLOW)
        }
    }
}