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
        val btnMasuk = findViewById<Button>(R.id.btnMasukKelas)
        val btnKeluar = findViewById<Button>(R.id.btnKeluarKelas)

        btnMasuk.text = "Kamu Sedang Berada Di: ${classRoom.nama}"
        btnMasuk.isEnabled = false

        // Tombol Keluar muncul
        btnKeluar.isEnabled = true
        btnKeluar.visibility = Button.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        val btnMasuk = findViewById<Button>(R.id.btnMasukKelas)
        val btnKeluar = findViewById<Button>(R.id.btnKeluarKelas)

        // tombol keluar
        btnKeluar.visibility = Button.GONE


        // Tombol MASUK KELAS
        btnMasuk.setOnClickListener {
            selectedRoom?.let { room ->
                // Sudah pilih kelas → tombol disable dan tampil nama kelas
                btnMasuk.isEnabled = false
                btnMasuk.text = "Berada di kelas ${room.nama}"
                // Tampilkan tombol Keluar
                btnKeluar.visibility = Button.VISIBLE
                btnKeluar.isEnabled = true
            } ?: run {
                // Belum pilih kelas → tampil fragment untuk pilih
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameContainer, listdatakelas())
                    .addToBackStack(null)
                    .commit()
                btnMasuk.text = "Pilih kelas..."
            }
        }


       // btn keluar kelas
        btnKeluar.setOnClickListener {
            selectedRoom = null
            btnMasuk.text = "Masuk Kelas"
            btnMasuk.isEnabled = true

            btnKeluar.visibility = Button.GONE
            btnKeluar.isEnabled = false
        }
    }
}
