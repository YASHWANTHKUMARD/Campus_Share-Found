package com.example.mat_base

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mat_base.ui.models.ItemType
import com.example.mat_base.ui.models.MaterialItem
import com.example.mat_base.ui.models.MaterialRepository
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.util.UUID

class PostLostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_lost)

        val postType = intent.getStringExtra("POST_TYPE") ?: "Post"
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.title = postType
        toolbar.setNavigationOnClickListener { finish() }

        val btnSubmit = findViewById<MaterialButton>(R.id.btnSubmit)
        val etTitle = findViewById<TextInputEditText>(R.id.etItemName)
        val etDesc = findViewById<TextInputEditText>(R.id.etDescription)

        btnSubmit.setOnClickListener {
            val title = etTitle.text.toString()
            val desc = etDesc.text.toString()

            if (title.isBlank()) {
                etTitle.error = "Please enter a title"
                return@setOnClickListener
            }

            showConfirmationDialog(postType, title, desc)
        }
    }

    private fun showConfirmationDialog(type: String, title: String, desc: String) {
        AlertDialog.Builder(this)
            .setTitle("Confirm Submission")
            .setMessage("Are you sure you want to post this $type?")
            .setPositiveButton("Post") { _, _ ->
                val itemType = when (type) {
                    "Share Request" -> ItemType.SHARE
                    "Report Lost Item" -> ItemType.LOST
                    "Report Found Item" -> ItemType.FOUND
                    else -> ItemType.SHARE
                }

                val newItem = MaterialItem(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    description = desc,
                    ownerName = "Current User",
                    type = itemType,
                    category = "General",
                    timestamp = "Just now"
                )

                MaterialRepository.addItem(newItem)

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
