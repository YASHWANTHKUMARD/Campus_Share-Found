package com.example.mat_base

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class PostLostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_lost)

        val postType = intent.getStringExtra("POST_TYPE") ?: "Post"
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.title = postType
        toolbar.setNavigationOnClickListener { finish() }

        val btnSubmit = findViewById<MaterialButton>(R.id.btnSubmit)
        val etTitle = findViewById<TextInputEditText>(R.id.etItemName) // Assuming ID from layout

        btnSubmit.setOnClickListener {
            showConfirmationDialog(postType)
        }
    }

    private fun showConfirmationDialog(type: String) {
        AlertDialog.Builder(this)
            .setTitle("Confirm Submission")
            .setMessage("Are you sure you want to post this $type?")
            .setPositiveButton("Post") { _, _ ->
                // Simulate success
                Toast.makeText(this, "$type submitted!", Toast.LENGTH_SHORT).show()
                sendSuccessNotification(type)
                finish()
            }
            .setNegativeButton("Review", null)
            .show()
    }

    private fun sendSuccessNotification(type: String) {
        val channelId = "campus_alerts"
        val notificationId = System.currentTimeMillis().toInt()

        val intent = Intent(this, DashboardActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Post Successful")
            .setContentText("Your $type has been published to the campus community.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            try {
                notify(notificationId, builder.build())
            } catch (e: SecurityException) {
                // Handle missing permission
            }
        }
    }
}
