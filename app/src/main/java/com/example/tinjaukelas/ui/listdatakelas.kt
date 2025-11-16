package com.example.tinjaukelas.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tinjaukelas.R
import com.example.tinjaukelas.adapter.ClassAdapter
import com.example.tinjaukelas.model.ClassRoom

class listdatakelas : Fragment() {

    private lateinit var adapter: ClassAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.listkelas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchBar = view.findViewById<EditText>(R.id.searchBar)
        val rv = view.findViewById<RecyclerView>(R.id.rvClass)

        val dummyData = listOf(
            ClassRoom("A.1.1", "RPL", aktif = "Available"),
            ClassRoom("A.2.1", "RPL", aktif = "Used"),
            ClassRoom("B.1.1", "TITL", aktif = "Available"),
            ClassRoom("C.1.1", "DKV", aktif = "Available" ),
            ClassRoom("C.2.1", "DKV", aktif = "Used"),
            ClassRoom("C.3.2", "TKJ", "Available"),
            ClassRoom("D.1.1", "TAV", aktif = "Available")
        )

        adapter = ClassAdapter(dummyData)
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

