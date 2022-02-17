package com.example.namesideias

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class NameAdapter(var letter:String,var context:Context): RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    var listNames:List<String>
    init{
        var list = when(LetterFragment.genreNames){
            GenreNames.BOYS -> R.array.BOYS
            else -> R.array.GIRLS
        }
        val data = context.resources.getStringArray(list).toList()
        listNames = data.filter { it.startsWith(letter) }.sorted()
    }

    class NameViewHolder(view: View): RecyclerView.ViewHolder(view){
        var button = view.findViewById<Button>(R.id.btnWord)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_word,parent,false)
        return NameViewHolder(view)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        var name = listNames[position]
        var context = holder.itemView.context
       holder.button.text = name
        holder.button.setOnClickListener {
            var queryUri: Uri = Uri.parse("${NameFragment.SEARCH_PREFIX}$name")
            var intent = Intent(Intent.ACTION_VIEW,queryUri)
            context.startActivity(intent)
        }


    }

    override fun getItemCount() = listNames.size
}