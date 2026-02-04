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

    private val CHANNEL_ID_SIMPLE = "simple_notifications"
    private val CHANNEL_ID_HIGH = "high_priority_notifications"
    private val CHANNEL_ID_PROGRESS = "progress_notifications"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashboard)

        createNotificationChannels()
        requestNotificationPermission()

        // Existing Card Click Listeners
        findViewById<MaterialCardView>(R.id.cardPostLost).setOnClickListener {
            startActivity(Intent(this, PostLostActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardViewFound).setOnClickListener {
            startActivity(Intent(this, ViewFoundActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardShareItems).setOnClickListener {
            startActivity(Intent(this, MaterialSharingActivity::class.java))
        }

        // 1. Simple Status Bar / Drawer Notification
        findViewById<Button>(R.id.btnBasicNotification).setOnClickListener {
            showSimpleNotification()
        }

        // 2. Heads-Up Notification (High Priority)
        findViewById<Button>(R.id.btnHeadsUpNotification).setOnClickListener {
            showHeadsUpNotification()
        }

        // 3. Progress-Centric Notification
        findViewById<Button>(R.id.btnProgressNotification).setOnClickListener {
            showProgressNotification()
        }

        // 4. Toast & Dialog Demo
        findViewById<Button>(R.id.btnDialogToast).setOnClickListener {
            showDialogAndToast()
        }
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(NotificationManager::class.java)

            // Simple Channel
            manager.createNotificationChannel(NotificationChannel(
                CHANNEL_ID_SIMPLE, "General Updates", NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "Used for standard app notifications" })

            // High Priority Channel (Required for Heads-up)
            manager.createNotificationChannel(NotificationChannel(
                CHANNEL_ID_HIGH, "Urgent Alerts", NotificationManager.IMPORTANCE_HIGH
            ).apply { description = "Used for time-sensitive alerts" })

            // Progress Channel
            manager.createNotificationChannel(NotificationChannel(
                CHANNEL_ID_PROGRESS, "Task Progress", NotificationManager.IMPORTANCE_LOW
            ).apply { description = "Used for background tasks" })
        }
    }

    private fun showSimpleNotification() {
        val intent = Intent(this, DashboardActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID_SIMPLE)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("New Item Found!")
            .setContentText("A set of keys was found in the library. Tap to view.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notify(1, builder)
    }

    private fun showHeadsUpNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID_HIGH)
            .setSmallIcon(android.R.drawable.stat_sys_warning)
            .setContentTitle("Urgent: Security Alert")
            .setContentText("Someone is trying to access your shared item.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setFullScreenIntent(null, true) // Helps trigger heads-up on some devices

        notify(2, builder)
    }

    private fun showProgressNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID_PROGRESS)
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setContentTitle("Uploading Image")
            .setContentText("Your lost item post is being uploaded...")
            .setOngoing(true)
            .setOnlyAlertOnce(true)

        val progressMax = 100
        var progressCurrent = 0

        Thread {
            while (progressCurrent <= progressMax) {
                builder.setProgress(progressMax, progressCurrent, false)
                notify(3, builder)
                Thread.sleep(500)
                progressCurrent += 10
            }
            builder.setContentText("Upload complete")
                .setProgress(0, 0, false)
                .setOngoing(false)
            notify(3, builder)
        }.start()
    }

    private fun showDialogAndToast() {
        Toast.makeText(this, "Launching Dialog...", Toast.LENGTH_SHORT).show()

        AlertDialog.Builder(this)
            .setTitle("Confirm Action")
            .setMessage("Do you want to clear all notification history?")
            .setPositiveButton("Yes") { _, _ ->
                Toast.makeText(this, "History Cleared", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun notify(id: Int, builder: NotificationCompat.Builder) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            NotificationManagerCompat.from(this).notify(id, builder.build())
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 101)
            }
        }
    }
}
