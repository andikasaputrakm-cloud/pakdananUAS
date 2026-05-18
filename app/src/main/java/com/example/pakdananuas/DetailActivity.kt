package com.example.pakdananuas

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    lateinit var imgDetail: ImageView
    lateinit var txtDetail: TextView
    lateinit var txtDesc: TextView

    lateinit var txtHarga: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // HUBUNGKAN XML
        imgDetail = findViewById(R.id.imgDetail)
        txtDetail = findViewById(R.id.txtDetail)
        txtDesc = findViewById(R.id.txtDesc)
        txtHarga = findViewById(R.id.txtHarga)

        // AMBIL DATA DARI INTENT
        val nama = intent.getStringExtra("nama")
        val gambar = intent.getIntExtra("gambar", 0)
        val harga = intent.getStringExtra("harga")
        val deskripsi = intent.getStringExtra("deskripsi")
        // TAMPILKAN DATA
        txtDetail.text = nama
        imgDetail.setImageResource(gambar)

        // DESKRIPSI
        txtDesc.text = deskripsi
        txtHarga.text = harga
    }
}