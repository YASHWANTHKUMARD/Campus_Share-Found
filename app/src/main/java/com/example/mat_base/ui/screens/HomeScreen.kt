package com.example.mat_base.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mat_base.ui.components.MaterialItemCard
import com.example.mat_base.ui.models.ItemType
import com.example.mat_base.ui.models.MaterialItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val dummyItems = listOf(
        MaterialItem("1", "Dell XPS 13", "Need a laptop for the weekend for a project.", "John Doe", ItemType.SHARE, "Electronics", "2h ago"),
        MaterialItem("2", "Scientific Calculator", "Found near the library.", "Jane Smith", ItemType.FOUND, "Stationery", "5h ago"),
        MaterialItem("3", "Blue Notebook", "Lost my math notes. Please help!", "Alice Brown", ItemType.LOST, "Stationery", "1d ago"),
        MaterialItem("4", "Lab Coat", "Extra lab coat available for borrowing.", "Bob Wilson", ItemType.SHARE, "Clothing", "3d ago")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MAT-BASE Home") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Add post */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add Post")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(8.dp)
        ) {
            item {
                Text(
                    text = "Recent Updates",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(8.dp)
                )
            }
            items(dummyItems) { item ->
                MaterialItemCard(item = item)
            }
        }
    }
}
