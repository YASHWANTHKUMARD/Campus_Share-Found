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

        // Updated dummy data with more items
        val foundItems = listOf(
            MaterialItem("1", "Keys with Red Keychain", "Found in the cafeteria near the entrance.", "Security Office", ItemType.FOUND, "Personal", "1h ago"),
            MaterialItem("2", "Blue Water Bottle", "Hydroflask found in Room 302.", "Admin Desk", ItemType.FOUND, "Misc", "4h ago"),
            MaterialItem("3", "Glasses Case", "Found in the library reading area.", "Librarian", ItemType.FOUND, "Accessories", "1d ago"),
            MaterialItem("4", "Black Umbrella", "Left in the gym locker room area.", "Gym Staff", ItemType.FOUND, "Personal", "2h ago"),
            MaterialItem("5", "Student ID Card", "Found near the Student Center fountain.", "Information Desk", ItemType.FOUND, "Documents", "30m ago"),
            MaterialItem("6", "Brown Leather Wallet", "Found in the parking lot B sector.", "Campus Police", ItemType.FOUND, "Personal", "5h ago"),
            MaterialItem("7", "Wireless Earbuds Case", "White case found in the computer lab.", "Lab Assistant", ItemType.FOUND, "Electronics", "2d ago"),
            MaterialItem("8", "Scientific Calculator", "Casio calculator found in Lecture Hall 4.", "Prof. Smith", ItemType.FOUND, "Stationery", "3h ago")
        )

        recyclerView.adapter = MaterialSharingActivity.MaterialAdapter(foundItems)
    }
}
