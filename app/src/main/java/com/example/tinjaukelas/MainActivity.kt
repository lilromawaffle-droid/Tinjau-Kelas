package com.example.tinjaukelas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.tinjaukelas.model.ClassRoom
import com.example.tinjaukelas.ui.listdatakelas

class MainActivity : AppCompatActivity(), listdatakelas.OnClassSelectedListener {

    var selectedRoom: ClassRoom? = null

    override fun onClassSelected(classRoom: ClassRoom) {
        selectedRoom = classRoom
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        val btnMasuk = findViewById<Button>(R.id.masukkelas)
        val btnCheck = findViewById<Button>(R.id.checkkelas)



        // Tombol MASUK KELAS
        btnMasuk.setOnClickListener {
            selectedRoom?.let { room ->
                btnMasuk.text =
                    "Kamu memilih kelas: ${room.nama} (${room.jurusan})"
            } ?: run {
                btnMasuk.text = "Belum memilih kelas"
            }
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
