package com.example.tinjaukelas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tinjaukelas.R
import com.example.tinjaukelas.model.ClassRoom

class ClassAdapter(private val classList: List<ClassRoom>) :
    RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {

    private var filteredList: List<ClassRoom> = classList

    inner class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRoomName: TextView = itemView.findViewById(R.id.tvRoomName)
        val tvJurusan: TextView = itemView.findViewById(R.id.tvJurusan)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.keterangankelas, parent, false)
        return ClassViewHolder(view)
    }

    override fun getItemCount(): Int = filteredList.size

override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val classroom = filteredList[position]

        holder.tvRoomName.text = classroom.nama
        holder.tvJurusan.text = classroom.jurusan

        val isAvailable = classroom.aktif.equals("Available", ignoreCase = true)

        holder.tvStatus.text = classroom.aktif

        holder.tvStatus.setTextColor(
            ContextCompat.getColor(
                holder.itemView.context,
                if (isAvailable) android.R.color.holo_green_dark else android.R.color.holo_red_dark
            )
        )
    }


    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            classList
        } else {
            classList.filter {
                it.nama.contains(query, ignoreCase = true)
            }
        }

        notifyDataSetChanged()
    }
}


