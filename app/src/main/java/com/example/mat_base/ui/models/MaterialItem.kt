package com.example.mat_base.ui.models

enum class ItemType {
    SHARE, LOST, FOUND
}

data class MaterialItem(
    val id: String,
    val title: String,
    val description: String,
    val ownerName: String,
    val type: ItemType,
    val category: String, // e.g., "Electronics", "Stationery"
    val timestamp: String
)
