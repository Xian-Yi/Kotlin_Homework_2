package com.example.alertdialog_kotlin

import android.os.Bundle
import android.view.Gravity

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private fun showToast() {
        // Step 1: 初始化 Toast 物件
        val toast = Toast(this@MainActivity)
        // Step 2: 設定 Toast 在畫面中的顯示位置 (頂端，x偏移量為0，y偏移量為50)
        toast.setGravity(Gravity.TOP, 0, 50)
        // Step 3: 設定 Toast 顯示的持續時間 (短暫)
        toast.duration = Toast.LENGTH_SHORT
        // Step 4: 取得自定義的 layout
        val inflater = layoutInflater
        val layout: View = inflater.inflate(
            R.layout.custom_toast,
            findViewById<ViewGroup>(R.id.custom_toast_root)
        )
        // Step 5: 將自定義的 layout 設置給 Toast
        toast.view = layout
        // Step 6: 顯示 Toast
        toast.show()
    }

    private fun showListDialog() {
        // 建立一個字串陣列，用來存放列表中的項目
        val list = arrayOf("message1", "message2", "message3", "message4", "message5")
        // 建立一個 AlertDialog.Builder 物件，並設定上下文
        val dialogList = AlertDialog.Builder(this@MainActivity)
        // 設定對話框的標題
        dialogList.setTitle("使用LIST呈現")
        // 設定對話框的列表項目，並指定點擊事件的監聽器
        dialogList.setItems(list) { _, i ->
            // 顯示一個 Toast，顯示使用者所選的項目
            Toast.makeText(this@MainActivity, "你選的是${list[i]}", Toast.LENGTH_SHORT).show()
        }
        // 顯示對話框
        dialogList.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            // 建立 AlertDialog.Builder 物件
            val dialog = AlertDialog.Builder(this@MainActivity)
            dialog.setTitle("請選擇功能")
            dialog.setMessage("請根據下方按鈕選擇要顯示的物件")

            // 設定取消按鈕
            dialog.setNeutralButton("取消") { _, _ ->
                Toast.makeText(this@MainActivity, "dialog關閉", Toast.LENGTH_SHORT).show()
            }

            // 設定自定義Toast按鈕
            dialog.setNegativeButton("自定義Toast") { _, _ ->
                showToast() // 執行顯示自定義 Toast 的方法
            }

            // 設定顯示列表按鈕
            dialog.setPositiveButton("顯示list") { _, _ ->
                showListDialog() // 執行顯示列表的對話框的方法
            }
            dialog.show() // 顯示對話框
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
