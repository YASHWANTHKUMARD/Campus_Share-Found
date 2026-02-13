package com.example.mat_base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mat_base.ui.models.MaterialRepository
import com.google.android.material.appbar.MaterialToolbar

class ViewFoundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_found)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        val recyclerView = findViewById<RecyclerView>(R.id.rvFoundItems)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = MaterialAdapter(MaterialRepository.getFoundItems()) { item ->
            Toast.makeText(this, "Details for: ${item.title}", Toast.LENGTH_SHORT).show()
        }
    }
}
