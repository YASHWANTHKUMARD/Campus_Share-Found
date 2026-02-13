package com.example.mat_base

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.card.MaterialCardView

class DashboardActivity : AppCompatActivity() {

    private val CHANNEL_ID_ALERTS = "campus_alerts"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashboard)

        createNotificationChannels()
        requestNotificationPermission()

        // "Post Your Need" Card
        findViewById<MaterialCardView>(R.id.cardPostLost).setOnClickListener {
            showPostNeedDialog()
        }

        // "View Found Items" Card
        findViewById<MaterialCardView>(R.id.cardViewFound).setOnClickListener {
            startActivity(Intent(this, ViewFoundActivity::class.java))
        }

        // "View Requests" Card
        findViewById<MaterialCardView>(R.id.cardShareItems).setOnClickListener {
            startActivity(Intent(this, MaterialSharingActivity::class.java))
        }

        // System status button (Optional: just to test permission/service)
        findViewById<Button>(R.id.btnBasicNotification).setOnClickListener {
            Toast.makeText(this, "Notification Service is Running", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showPostNeedDialog() {
        val options = arrayOf("1. Share Request", "2. Report Lost Item", "3. Report Found Item")
        
        AlertDialog.Builder(this)
            .setTitle("Post Your Need")
            .setItems(options) { _, which ->
                val intent = Intent(this, PostLostActivity::class.java)
                when (which) {
                    0 -> intent.putExtra("POST_TYPE", "Share Request")
                    1 -> intent.putExtra("POST_TYPE", "Report Lost Item")
                    2 -> intent.putExtra("POST_TYPE", "Report Found Item")
                }
                startActivity(intent)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(NotificationManager::class.java)
            val channel = NotificationChannel(
                CHANNEL_ID_ALERTS, "Campus Share & Found Updates", NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for item matches and request updates"
            }
            manager.createNotificationChannel(channel)
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 101)
            }
        }
    }

    // Helper function for other activities to send notifications
    fun sendNotification(title: String, message: String) {
        val intent = Intent(this, DashboardActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID_ALERTS)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            NotificationManagerCompat.from(this).notify(System.currentTimeMillis().toInt(), builder.build())
        }
    }
}
