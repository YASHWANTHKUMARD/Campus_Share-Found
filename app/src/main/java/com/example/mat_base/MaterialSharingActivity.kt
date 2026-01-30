package com.example.mat_base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mat_base.ui.models.ItemType
import com.example.mat_base.ui.models.MaterialItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.chip.Chip

class MaterialSharingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.material_sharing)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dummyItems = listOf(
            MaterialItem("1", "Dell XPS 13", "Need a laptop for the weekend for a project.", "John Doe", ItemType.SHARE, "Electronics", "2h ago"),
            MaterialItem("2", "Scientific Calculator", "Found near the library.", "Jane Smith", ItemType.FOUND, "Stationery", "5h ago"),
            MaterialItem("3", "Blue Notebook", "Lost my math notes. Please help!", "Alice Brown", ItemType.LOST, "Stationery", "1d ago"),
            MaterialItem("4", "Lab Coat", "Extra lab coat available for borrowing.", "Bob Wilson", ItemType.SHARE, "Clothing", "3d ago")
        )

        recyclerView.adapter = MaterialAdapter(dummyItems)
    }

    class MaterialAdapter(private val items: List<MaterialItem>) : RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.itemTitle)
            val desc: TextView = view.findViewById(R.id.itemDescription)
            val owner: TextView = view.findViewById(R.id.itemOwner)
            val time: TextView = view.findViewById(R.id.itemTimestamp)
            val badge: Chip = view.findViewById(R.id.itemTypeBadge)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_material, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.title.text = item.title
            holder.desc.text = item.description
            holder.owner.text = "By: ${item.ownerName}"
            holder.time.text = item.timestamp
            holder.badge.text = item.type.name
            
            // Set badge color based on type
            val context = holder.itemView.context
            when(item.type) {
                ItemType.SHARE -> holder.badge.setChipBackgroundColorResource(android.R.color.holo_green_dark)
                ItemType.LOST -> holder.badge.setChipBackgroundColorResource(android.R.color.holo_red_dark)
                ItemType.FOUND -> holder.badge.setChipBackgroundColorResource(android.R.color.holo_blue_dark)
            }
        }

        override fun getItemCount() = items.size
    }
}
