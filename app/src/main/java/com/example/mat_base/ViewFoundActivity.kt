package com.example.mat_base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mat_base.ui.models.ItemType
import com.example.mat_base.ui.models.MaterialItem
import com.google.android.material.appbar.MaterialToolbar

class ViewFoundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_found)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        val recyclerView = findViewById<RecyclerView>(R.id.rvFoundItems)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Dummy data for Found items
        val foundItems = listOf(
            MaterialItem("1", "Keys with Red Keychain", "Found in the cafeteria near the entrance.", "Security Office", ItemType.FOUND, "Personal", "1h ago"),
            MaterialItem("2", "Water Bottle", "Blue hydroflask found in Room 302.", "Admin", ItemType.FOUND, "Misc", "4h ago"),
            MaterialItem("3", "Glasses Case", "Found in the library reading area.", "Librarian", ItemType.FOUND, "Accessories", "1d ago")
        )

        recyclerView.adapter = MaterialSharingActivity.MaterialAdapter(foundItems)
    }
}
