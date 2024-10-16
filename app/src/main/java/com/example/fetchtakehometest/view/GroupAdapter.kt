package com.example.fetchtakehometest.view
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchtakehometest.databinding.GroupListItemBinding
import com.example.fetchtakehometest.model.ItemData

class GroupAdapter(
    private var groupedItems: Map<Int, List<ItemData>>,
    private val onGroupClick: (Int) -> Unit
) : RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {

    private lateinit var binding: GroupListItemBinding

    override fun getItemCount() = groupedItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = GroupListItemBinding.inflate(layoutInflater, parent, false)
        return GroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val listId = groupedItems.keys.elementAt(position)
        val items = groupedItems[listId] ?: listOf()
        holder.bind(listId, items)
    }

    fun updateData(newGroupedItems: Map<Int, List<ItemData>>) {
        this.groupedItems = newGroupedItems
        notifyDataSetChanged()
    }

    inner class GroupViewHolder(private val itemView: GroupListItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        fun bind(listId: Int, items: List<ItemData>) = with(itemView) {
            binding.apply {
                tvListid.text = "List ID: $listId"

                btnListview.setOnClickListener {
                    onGroupClick(listId)
                }
            }
        }
    }
}