package com.example.tinjaukelas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tinjaukelas.ui.listdatakelas

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        val btnMasuk = findViewById<Button>(R.id.masukkelas)
        val btnCheck = findViewById<Button>(R.id.checkkelas)

        val namaKelas = "XII RPL 1"

        // Tombol MASUK KELAS
        btnMasuk.setOnClickListener {
            btnMasuk.text = "Anda sedang berada dalam ruangan: $namaKelas"
            btnMasuk.setBackgroundColor(Color.parseColor("#4CAF50"))
            btnMasuk.setTextColor(Color.WHITE)
        }

        // Tombol CHECK KELAS
        btnCheck.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameContainer, listdatakelas())
                .addToBackStack(null)
                .commit()
        }
    }
}
