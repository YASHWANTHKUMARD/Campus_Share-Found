package com.example.mat_base.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class ItemType {
    SHARE, LOST, FOUND
}

@Parcelize
data class MaterialItem(
    val id: String,
    val title: String,
    val description: String,
    val ownerName: String,
    val type: ItemType,
    val category: String, // e.g., "Electronics", "Stationery"
    val timestamp: String
) : Parcelable

object MaterialRepository {
    private val _items = mutableListOf<MaterialItem>(
        MaterialItem("1", "Dell XPS 13", "Need a laptop for the weekend for a project.", "John Doe", ItemType.SHARE, "Electronics", "2h ago"),
        MaterialItem("2", "Scientific Calculator", "Found near the library.", "Jane Smith", ItemType.FOUND, "Stationery", "5h ago"),
        MaterialItem("3", "Blue Notebook", "Lost my math notes. Please help!", "Alice Brown", ItemType.LOST, "Stationery", "1d ago"),
        MaterialItem("4", "Lab Coat", "Extra lab coat available for borrowing.", "Bob Wilson", ItemType.SHARE, "Clothing", "3d ago")
    )

    fun getItems(): List<MaterialItem> = _items.toList()
    
    fun getFoundItems(): List<MaterialItem> = _items.filter { it.type == ItemType.FOUND }

    fun addItem(item: MaterialItem) {
        _items.add(0, item) // Add new items at the top
    }

    fun updateItemType(itemId: String, newType: ItemType) {
        val index = _items.indexOfFirst { it.id == itemId }
        if (index != -1) {
            val item = _items[index]
            _items[index] = item.copy(type = newType)
        }
    }
}
