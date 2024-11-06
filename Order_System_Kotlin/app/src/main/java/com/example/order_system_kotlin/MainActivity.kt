package com.example.order_system_kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.Insets
import androidx.activity.enableEdgeToEdge

class MainActivity : AppCompatActivity() {

    private lateinit var tvMeal: TextView
    private lateinit var btnSelect: Button

    private val mStartForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            intent?.extras?.let { bundle ->
                val drink = bundle.getString("drink")
                val sugar = bundle.getString("sugar")
                val ice = bundle.getString("ice")
                tvMeal.text = "飲料: $drink\n甜度: $sugar\n冰塊: $ice"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvMeal = findViewById(R.id.tv_meal)
        btnSelect = findViewById(R.id.btn_choice)

        btnSelect.setOnClickListener {
            mStartForResult.launch(Intent(this, MainActivity2::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars: Insets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
