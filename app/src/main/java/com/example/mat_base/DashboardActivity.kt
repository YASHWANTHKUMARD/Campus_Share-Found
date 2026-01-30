package com.example.mat_base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashboard)

        findViewById<MaterialCardView>(R.id.cardPostLost).setOnClickListener {
            startActivity(Intent(this, PostLostActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardViewFound).setOnClickListener {
            startActivity(Intent(this, ViewFoundActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardShareItems).setOnClickListener {
            startActivity(Intent(this, MaterialSharingActivity::class.java))
        }
    }
}
