package com.example.toasthandler

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Обработка системных отступов
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + 16, systemBars.top + 16, systemBars.right + 16, systemBars.bottom + 16)
            insets
        }

        // 1. Находим объект Button по его id
        val button = findViewById<Button>(R.id.button_ok)

        // 2. Устанавливаем обработчик OnClickListener
        button.setOnClickListener {
            // Создаем и отображаем Toast с текстом "Кнопка ОК"
            Toast.makeText(this, "Кнопка ОК", Toast.LENGTH_SHORT).show()
        }
    }
}