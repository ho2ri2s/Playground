package com.example.uianimation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.uianimation.databinding.ItemListBinding

data class MainListModel(val id: Int)

class MainListAdapter(private val context: Context) :
    RecyclerView.Adapter<MainListAdapter.ListViewHolder>() {

    // filteredItems is a static field to simulate filtering of random items
    private val filteredItems = intArrayOf(2, 5, 6, 8, 12)
    private val modelList = List(20) { MainListModel(it) }
    private val modelListFiltered = modelList.filter { it.id !in filteredItems }
    private val adapterList: List<MainListModel>
        get() = if (isFiltered) modelListFiltered else modelList

    /** Variable used to filter adapter items. 'true' if filtered and 'false' if not */
    var isFiltered = false
        set(value) {
            field = value
            val diff = MainListDiffUtil(
                if (field) modelList else modelListFiltered,
                if (field) modelListFiltered else modelList
            )
            DiffUtil.calculateDiff(diff).dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = adapterList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
    }


    class ListViewHolder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val expandView: View = binding.expandView
        val chevron: View = binding.chevron
        val cardContainer: View = binding.cardContainer
        val scaleContainer: View = binding.scaleContainer
        val listItemFg: View = binding.listItemFg
    }
}

