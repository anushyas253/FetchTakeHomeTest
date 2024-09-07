package com.example.fetchtakehometest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchtakehometest.model.ItemData
import com.example.fetchtakehometest.databinding.ListItemBinding

class ItemAdapter(private var notesList: List<ItemData>) : RecyclerView.Adapter<ItemAdapter.NoteViewHolder>() {

    private lateinit var binding: ListItemBinding

    override fun getItemCount() = notesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notesList[position])
    }
    fun updateItems(newItems: List<ItemData>) {
        notesList = newItems
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val itemView: ListItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        fun bind(item: ItemData) = with(item) {
            binding.apply {
                tvListId.text = listId.toString()
                tvName.text = name


            }
        }
    }
}
