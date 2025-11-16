package com.example.tinjaukelas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        val btnMasuk = findViewById<Button>(R.id.masukkelas)
        val btnCheck = findViewById<Button>(R.id.checkkelas)

        val namaKelas = "XII RPL 1" // contoh, bisa diganti nanti

        btnMasuk.setOnClickListener {
            btnMasuk.text = "Anda sedang berada dalam ruangan: $namaKelas"
            btnMasuk.setBackgroundColor(Color.parseColor("#4CAF50")) // hijau
            btnMasuk.setTextColor(Color.WHITE)
        }
    }
}
