package com.example.fetchtakehometest
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchtakehometest.ItemAdapter
import com.example.fetchtakehometest.databinding.ActivityListDetailsBinding
import com.example.fetchtakehometest.model.ItemData
import com.example.fetchtakehometest.viewmodel.ItemViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListDetailsBinding
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var groupAdapter: GroupAdapter
    private val notesList = ArrayList<ItemData>()
    private val groupedItems = mutableMapOf<Int, List<ItemData>>()

    val viewModel: ItemViewModel by viewModels()

    private val mainScope = MainScope()
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun prepareData() {
        viewModel.items.observe(this, Observer { items ->
            job?.cancel() // Cancel any ongoing job if needed
            job = mainScope.launch {
                withContext(Dispatchers.Default) {
                    items?.let {
                        // Clear existing items


                        val filteredList = it.filter { item -> !item.name.isNullOrBlank() }

                        // Sort by listId and then by name
                        val sortedList = filteredList.sortedWith(
                            compareBy<ItemData> { item -> item.listId }.thenBy { item -> item.name }
                        )
                        notesList.addAll(sortedList)

                        val m =sortedList.groupBy { it.listId }
                        // Group by listId
                        groupedItems.putAll(m)

                        }
                        // Update adapters
                        withContext(Dispatchers.Main) {
                            updateAdapters()
                            displayList()
                        }
                }

            }

        })
        viewModel.fetchItems()
    }

    private fun updateAdapters() {
        // Setup Group Adapter
        binding.rcViewgroup.layoutManager = LinearLayoutManager(this)
        groupAdapter = GroupAdapter(groupedItems) { listId ->
            // Filter items based on listId
            val filteredItems= groupedItems[listId] ?: mutableListOf()
            itemAdapter.updateItems(filteredItems)
            groupAdapter.updateData(groupedItems)
        }
        binding.rcView.layoutManager = LinearLayoutManager(this)
        itemAdapter = ItemAdapter(notesList)
        binding.rcViewgroup.adapter = groupAdapter
        itemAdapter.notifyDataSetChanged()
    }

    private fun displayList() {
        itemAdapter = ItemAdapter(mutableListOf())
        binding.rcView.adapter = itemAdapter

    }

    private fun initViews() {
        prepareData()
        displayList()
    }
    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}