package com.example.fetchtakehometest.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchtakehometest.R
import com.example.fetchtakehometest.databinding.ActivityListDetailsBinding
import com.example.fetchtakehometest.model.ItemData
import com.example.fetchtakehometest.viewmodel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListDetailsBinding
    private lateinit var itemAdapter: ItemAdapter

    private val filterByName: (ItemData) -> Boolean = { item ->
        item.name != null && item.name != "null" && item.name.isNotEmpty()
    }
    private val filterNone: (ItemData) -> Boolean = { true }

    private val comparatorByFirst = compareBy<ItemData> { it.listId }
    private val comparatorByFirstSecond = compareBy<ItemData> { it.listId }
        .thenBy { item ->
            item.name?.replace(Regex("[^0-9]"), "")?.toIntOrNull() ?: Int.MAX_VALUE
        }

    private var sortingCriteria = MutableLiveData(0)
    private var isFilterByName = MutableLiveData(false)

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinners()
        setupRecyclerView()
        observeViewModel()
        observeFilterAndSortingChanges()

        binding.btnGroup.setOnClickListener {
            val intent = Intent(this, GroupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupSpinners() {
        setupSortingSpinner()
        setupFilterSpinner()
    }

    private fun setupSortingSpinner() {
        val sortingAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.my_spinner_items,
            android.R.layout.simple_spinner_item
        )
        binding.sortSpinner.adapter = sortingAdapter
        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sortingCriteria.value = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupFilterSpinner() {
        val filterAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.my_filter_items,
            android.R.layout.simple_spinner_item
        )
        binding.filterSp.adapter = filterAdapter
        binding.filterSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                isFilterByName.value = position != 0
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupRecyclerView() {
        itemAdapter = ItemAdapter()
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = itemAdapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.items.observe(this@ListDetailsActivity) { items ->
                if (items != null && items.isNotEmpty()) {
                    applyFilterAndSorting(items)
                }
            }
        }
        viewModel.fetchItems()
    }

    private fun observeFilterAndSortingChanges() {
        sortingCriteria.observe(this) {
            applyFilterAndSorting(viewModel.items.value ?: emptyList())
        }

        isFilterByName.observe(this) {
            applyFilterAndSorting(viewModel.items.value ?: emptyList())
        }
    }

    private fun applyFilterAndSorting(items: List<ItemData>) {

        val comparator = when (sortingCriteria.value) {
            1 -> comparatorByFirst
            2 -> comparatorByFirstSecond
            else -> Comparator<ItemData> { _, _ -> 0 }
        }

        val filter = if (isFilterByName.value == true) filterByName else filterNone

        val filteredSortedItems = items
            .filter(filter)
            .sortedWith(comparator)
            .distinctBy { it.id }

        itemAdapter.submitList(filteredSortedItems)
        Log.d("ListDetailsActivity", "Filtered and Sorted List Size: ${filteredSortedItems.size}")
        val firstVisibleItemPosition = (binding.rcView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (firstVisibleItemPosition == 0) {
            binding.rcView.post {
                binding.rcView.scrollToPosition(0)
            }
        }
    }
}
