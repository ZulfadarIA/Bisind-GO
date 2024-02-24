package com.zulfadar.bisindgo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.R
import com.zulfadar.bisindgo.data.Vocabulary

class ListVocabularyAdapter(private val listVocab: ArrayList<Vocabulary>, val listener: (Vocabulary) -> Unit) : RecyclerView.Adapter<ListVocabularyAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindView(listVocab[position], listener)
        val currentItem = listVocab[position]
        holder.ivItemPhoto.setImageResource(currentItem.photo)
        holder.tvKosakata.text = currentItem.kosakata
        itemCount
    }

    override fun getItemCount(): Int = listVocab.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivItemPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)
        val tvKosakata: TextView = itemView.findViewById(R.id.tv_item_name)

        fun bindView(Vocabulary: Vocabulary, listener: (Vocabulary) -> Unit) {
            ivItemPhoto.setImageResource(Vocabulary.photo)
            tvKosakata.text = Vocabulary.kosakata
            itemView.setOnClickListener{
                listener(Vocabulary)
            }
        }
    }
}


