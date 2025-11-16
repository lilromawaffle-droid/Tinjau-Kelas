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
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.keterangankelas, parent, false)
        return ClassViewHolder(view)
    }

    override fun getItemCount(): Int = classList.size

override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val classroom = classList[position]

        holder.tvRoomName.text = classroom.nama

        val isEmpty = classroom.aktif.equals("N", ignoreCase = true)

        holder.tvStatus.text = if (isEmpty) "Empty" else "Occupied"

        holder.tvStatus.setTextColor(
            ContextCompat.getColor(
                holder.itemView.context,
                if (isEmpty) android.R.color.holo_green_dark else android.R.color.holo_red_dark
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


