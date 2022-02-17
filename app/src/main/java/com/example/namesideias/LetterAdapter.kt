package com.example.namesideias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class LetterAdapter: RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {
    val list = ('A').rangeTo('Z').toList()

    class LetterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val button = view.findViewById<Button>(R.id.btnWord)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.view_word,parent,false)
        return LetterViewHolder(layout)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val letter = list[position].toString()
        holder.button.text = letter
        holder.button.setOnClickListener {
            var action = LetterFragmentDirections.actionLetterFragmentToNameFragment(  letter)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}