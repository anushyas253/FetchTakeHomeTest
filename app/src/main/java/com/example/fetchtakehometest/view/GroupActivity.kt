package com.example.fetchtakehometest.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchtakehometest.databinding.ActivityGroupBinding
import com.example.fetchtakehometest.model.ItemData
import com.example.fetchtakehometest.viewmodel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupBinding
    private lateinit var groupAdapter: GroupAdapter

    private lateinit var itemAdapter: ItemAdapter

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
    }
    private fun setupRecyclerView() {
        groupAdapter = GroupAdapter(emptyMap()) { listId ->
            showItemsForListId(listId)
        }
        binding.grcView.layoutManager = LinearLayoutManager(this)
        binding.grcView.adapter = groupAdapter

        // Item Adapter
        itemAdapter = ItemAdapter()
        binding.ircView.layoutManager = LinearLayoutManager(this)
        binding.ircView.adapter = itemAdapter
    }
    private fun observeViewModel() {
        viewModel.items.observe(this) { items ->
            if (items != null && items.isNotEmpty()) {
                val groupedItems = groupItemsByListId(items)
                groupAdapter.updateData(groupedItems)
            }
        }

        viewModel.fetchItems()
    }
    private fun groupItemsByListId(items: List<ItemData>): Map<Int, List<ItemData>> {
        return items.filter { it.name != null && it.name.isNotEmpty() } // Filter out items with null or empty names
            .groupBy { it.listId }
    }
    private fun showItemsForListId(listId: Int) {

        val items = viewModel.items.value?.filter { it.listId == listId } ?: emptyList()
        itemAdapter.submitList(items)
        binding.ircView.visibility = View.VISIBLE
    }
}
