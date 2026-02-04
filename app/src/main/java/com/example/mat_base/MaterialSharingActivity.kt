package com.example.mat_base

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mat_base.ui.models.ItemType
import com.example.mat_base.ui.models.MaterialItem
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

        recyclerView.adapter = MaterialAdapter(dummyItems) { item ->
            handleItemClick(item)
        }
    }

    private fun handleItemClick(item: MaterialItem) {
        val action = when(item.type) {
            ItemType.SHARE -> "Accept Request"
            ItemType.LOST -> "I Found This"
            ItemType.FOUND -> "This is Mine"
        }

        AlertDialog.Builder(this)
            .setTitle(item.title)
            .setMessage("Do you want to $action for this item?")
            .setPositiveButton("Confirm") { _, _ ->
                Toast.makeText(this, "Action Confirmed!", Toast.LENGTH_SHORT).show()
                sendActionNotification(item, action)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun sendActionNotification(item: MaterialItem, action: String) {
        val channelId = "campus_alerts"
        val notificationId = System.currentTimeMillis().toInt()

        val intent = Intent(this, DashboardActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Update: $action")
            .setContentText("You have successfully responded to '${item.title}'.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            try {
                notify(notificationId, builder.build())
            } catch (e: SecurityException) {
                // Permission handled in Dashboard
            }
        }
    }

    class MaterialAdapter(
        private val items: List<MaterialItem>,
        private val onItemClick: (MaterialItem) -> Unit
    ) : RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {
        
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
            
            when(item.type) {
                ItemType.SHARE -> holder.badge.setChipBackgroundColorResource(android.R.color.holo_green_dark)
                ItemType.LOST -> holder.badge.setChipBackgroundColorResource(android.R.color.holo_red_dark)
                ItemType.FOUND -> holder.badge.setChipBackgroundColorResource(android.R.color.holo_blue_dark)
            }

            holder.itemView.setOnClickListener { onItemClick(item) }
        }

        override fun getItemCount() = items.size
    }
}
