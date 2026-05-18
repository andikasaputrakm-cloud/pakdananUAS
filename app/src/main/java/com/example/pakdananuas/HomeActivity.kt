package com.example.pakdananuas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    var btnMasuk: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnMasuk = findViewById<Button>(R.id.btnMasuk)

        btnMasuk?.setOnClickListener {
            val intent = Intent(this@HomeActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
