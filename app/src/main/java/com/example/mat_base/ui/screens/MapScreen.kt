package com.example.mat_base.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapScreen(onLocationSelected: (LatLng) -> Unit) {
    val initialLocation = LatLng(12.9716, 77.5946) // Default to Bangalore or some university center
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialLocation, 15f)
    }
    
    var markerPosition by remember { mutableStateOf(initialLocation) }

    Column(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.weight(1f),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                markerPosition = it
            }
        ) {
            Marker(
                state = MarkerState(position = markerPosition),
                title = "Logged in from here",
                snippet = "Click confirm to proceed"
            )
        }
        
        Button(
            onClick = { onLocationSelected(markerPosition) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Confirm Login Location")
        }
    }
}
