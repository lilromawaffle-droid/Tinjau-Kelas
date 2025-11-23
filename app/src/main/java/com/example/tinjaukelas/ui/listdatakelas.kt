package com.example.tinjaukelas.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tinjaukelas.adapter.ClassAdapter
import com.example.tinjaukelas.R
import com.example.tinjaukelas.model.ClassRoom

class listdatakelas : Fragment() {

    private lateinit var adapter: ClassAdapter
    private var selectedRoom: ClassRoom? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.listkelas, container, false)
    }

    interface OnClassSelectedListener {
        fun onClassSelected(classRoom: ClassRoom)
    }
    private var listener: OnClassSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnClassSelectedListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchBar = view.findViewById<EditText>(R.id.searchBar)
        val rv = view.findViewById<RecyclerView>(R.id.rvClass)

        val dummyData = listOf(
            ClassRoom("A.1.1", "RPL", aktif = "Available"),
            ClassRoom("A.1.2", "RPL", aktif = "Used"),
            ClassRoom("A.1.3", "TOI", aktif = "Available"),
            ClassRoom("A.1.4", "TOI", aktif = "Used"),
            ClassRoom("A.1.5", "DKV", aktif = "Available"),

            // B Block
            ClassRoom("B.1.1", "TITL", aktif = "Available"),
            ClassRoom("B.1.2", "TITL", aktif = "Used"),
            ClassRoom("B.1.3", "TOI", aktif = "Available"),
            ClassRoom("B.1.4", "TOI", aktif = "Used"),
            ClassRoom("B.1.5", "RPL", aktif = "Available"),

            // C Block
            ClassRoom("C.1.1", "DKV", aktif = "Available"),
            ClassRoom("C.1.2", "DKV", aktif = "Used"),
            ClassRoom("C.1.3", "TKJ", aktif = "Available"),
            ClassRoom("C.1.4", "TOI", aktif = "Used"),
            ClassRoom("C.2.1", "DKV", aktif = "Used"),
            ClassRoom("C.2.2", "TOI", aktif = "Available"),
            ClassRoom("C.2.3", "RPL", aktif = "Available"),
            ClassRoom("C.2.4", "TOI", aktif = "Used"),
            ClassRoom("C.2.5", "TKJ", aktif = "Available"),

            // D Block (sama seperti contoh lama)
            ClassRoom("D.1.1", "TAV", aktif = "Available"),

            // E Block
            ClassRoom("E.1.1", "TOI", aktif = "Available"),
            ClassRoom("E.1.2", "RPL", aktif = "Used"),
            ClassRoom("E.1.3", "DKV", aktif = "Available"),
            ClassRoom("E.1.4", "TOI", aktif = "Used"),
            ClassRoom("E.2.1", "TITL", aktif = "Available"),
            ClassRoom("E.2.2", "TOI", aktif = "Used"),
            ClassRoom("E.2.3", "TKJ", aktif = "Available"),
            ClassRoom("E.2.4", "TOI", aktif = "Used"),

            // F Block
            ClassRoom("F.1.1", "TOI", aktif = "Available"),
            ClassRoom("F.1.2", "RPL", aktif = "Used"),
            ClassRoom("F.1.3", "DKV", aktif = "Available"),
            ClassRoom("F.1.4", "TOI", aktif = "Used"),
            ClassRoom("F.1.5", "TKJ", aktif = "Available"),
            ClassRoom("F.1.6", "TOI", aktif = "Used"),
            ClassRoom("F.1.7", "RPL", aktif = "Available"),
            ClassRoom("F.2.1", "TOI", aktif = "Available"),
            ClassRoom("F.2.2", "DKV", aktif = "Used"),
            ClassRoom("F.2.3", "TOI", aktif = "Available"),
            ClassRoom("F.2.4", "TKJ", aktif = "Used")
        )





        adapter = ClassAdapter(dummyData) { room ->
            listener?.onClassSelected(room)
            Toast.makeText(requireContext(),
                "Kamu memilih kelas: ${room.nama} (${room.jurusan})",
                Toast.LENGTH_SHORT).show()
        }
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}