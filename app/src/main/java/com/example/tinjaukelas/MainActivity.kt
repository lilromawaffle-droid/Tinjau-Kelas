package com.example.tinjaukelas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tinjaukelas.adapter.ClassAdapter
import com.example.tinjaukelas.model.ClassRoom
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var isInRoom = false  // status pengguna
    private lateinit var adapter: ClassAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        val btnMasuk = findViewById<Button>(R.id.btnMasukKelas)
        val btnTinjau = findViewById<Button>(R.id.btnTinjauKelas)
        val rvKelas = findViewById<RecyclerView>(R.id.rvKelas)

        // Filter UI
        val filterContainer = findViewById<LinearLayout>(R.id.filterContainer)
        val spinnerJurusan = findViewById<Spinner>(R.id.spinnerJurusan)
        val searchBar = findViewById<EditText>(R.id.searchBar)

        filterContainer.visibility = View.GONE
        rvKelas.visibility = View.GONE

        // isi data kelas
        val kelasData = listOf(
            ClassRoom("A.1.1", "RPL", "Available"),
            ClassRoom("A.2.1", "RPL", "Used"),
            ClassRoom("B.1.1", "TITL", "Available"),
            ClassRoom("C.1.1", "DKV", "Available"),
            ClassRoom("C.2.1", "DKV", "Used"),
            ClassRoom("D.1.1", "TAV", "Available")
        )

        // -------------------------------
        // SETUP ADAPTER
        // -------------------------------
        adapter = ClassAdapter(
            kelasData,
            onItemClick = { kelas ->
                Toast.makeText(this, "Kelas dipilih: ${kelas.nama}", Toast.LENGTH_SHORT).show()
            }
        )

        rvKelas.layoutManager = LinearLayoutManager(this)
        rvKelas.adapter = adapter

        // -------------------------------
        // SPINNER FILTER JURUSAN
        // -------------------------------
        val jurusanList = listOf("Semua", "RPL", "TITL", "DKV", "TAV")

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            jurusanList
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerJurusan.adapter = spinnerAdapter

        spinnerJurusan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedJurusan = jurusanList[position]
                adapter.filterJurusan(selectedJurusan)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // -------------------------------
        // SEARCHBAR FILTER NAMA KELAS
        // -------------------------------
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filterNama(s.toString())
            }
        })

        // -------------------------------
        // TOMBOL MASUK / KELUAR KELAS
        // -------------------------------
        btnMasuk.setOnClickListener { view ->
            if (!isInRoom) {
                btnMasuk.text = "Berada di A.1.1"
                btnMasuk.setBackgroundColor(Color.GREEN)
                btnMasuk.setTextColor(Color.WHITE)
                isInRoom = true

            } else {
                Snackbar.make(view, "Keluar dari kelas A.1.1?", Snackbar.LENGTH_LONG)
                    .setAction("Keluar") {
                        btnMasuk.text = "Masuk kelas"
                        btnMasuk.setBackgroundColor(Color.LTGRAY)
                        btnMasuk.setTextColor(Color.BLACK)
                        isInRoom = false
                    }
                    .show()
            }
        }

        // tombol tampilkan recyclerview + filter
        btnTinjau.setOnClickListener {
            filterContainer.visibility = View.VISIBLE
            rvKelas.visibility = View.VISIBLE
        }
    }
}
