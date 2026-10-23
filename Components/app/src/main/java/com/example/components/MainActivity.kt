package com.example.components

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
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

        val etName = findViewById<EditText>(R.id.etName)
        val cbStudent = findViewById<CheckBox>(R.id.cbStudent)
        val btnProcess = findViewById<Button>(R.id.btnProcess)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnProcess.setOnClickListener {
            val name = etName.text.toString().trim()
            val isStudent = cbStudent.isChecked

            if (name.isEmpty()) {
                tvResult.text = "Пожалуйста, введите имя"
            } else {
                val status = if (isStudent) "студент ФИТ-241" else "гость"
                tvResult.text = "Привет, $name!\nТвой статус: $status"
            }
        }
    }
}