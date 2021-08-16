package com.mindinventory.circularcardsstackview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * This class uses for set endless list
 * @author MindInventory
 */
abstract class CircularAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = arrayListOf<T>()

    abstract fun createItemViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder

    abstract fun bindItemViewHolder(
        holder: RecyclerView.ViewHolder,
        item: T,
        actualPosition: Int,
        position: Int
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createItemViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val positionInList = position % items.size
        bindItemViewHolder(holder, items[positionInList], position, positionInList)
    }

    fun getActualItemCount() = items.size

    override fun getItemCount(): Int {
        return if (items.size > 1) Int.MAX_VALUE else items.size
    }

    fun setItems(items: ArrayList<T>, clearPreviousElements: Boolean = false) {
        if (clearPreviousElements) {
            this.items.clear()
        }
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getItems(): ArrayList<T> {
        return this.items
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}