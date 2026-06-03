package com.example.pakdananuas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var etInput: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnAZ: Button
    private lateinit var btnZA: Button

    private lateinit var dataList: ArrayList<Sandwich>
    private lateinit var originalList: ArrayList<Sandwich>
    private lateinit var adapter: SandwichAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // HUBUNGKAN KOMPONEN
        gridView = findViewById(R.id.gridView)
        etSearch = findViewById(R.id.etSearch)
        btnSearch = findViewById(R.id.btnSearch)
        etInput = findViewById(R.id.etInput)
        btnAdd = findViewById(R.id.btnAdd)
        btnAZ = findViewById(R.id.btnAZ)
        btnZA = findViewById(R.id.btnZA)

        // DATA
        dataList = ArrayList()
        isiDataAwal()

        originalList = ArrayList(dataList)

        // ADAPTER
        adapter = SandwichAdapter(this, dataList)
        gridView.adapter = adapter

        // DETAIL ACTIVITY
        gridView.setOnItemClickListener { _, _, position, _ ->
            val selectedSandwich = dataList[position]
            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra("nama", selectedSandwich.nama)
            intent.putExtra("gambar", selectedSandwich.gambar)
            intent.putExtra("deskripsi", selectedSandwich.deskripsi)
            intent.putExtra("harga", selectedSandwich.harga)
            startActivity(intent)
        }

        // TAMBAH MENU
        btnAdd.setOnClickListener {

            try {

                val namaBaru =
                    etInput.text.toString().trim()

                if (namaBaru.isEmpty()) {

                    etInput.error =
                        "Nama sandwich tidak boleh kosong"

                    Toast.makeText(
                        this,
                        "Silahkan isi nama menu",
                        Toast.LENGTH_SHORT)
                        
                    .show()

        } else {

            val newSandwich =
                Sandwich(
                    namaBaru,
                    R.drawable.sandwich,
                    "Menu Baru",
                    "Price TBD"
                )

            dataList.add(newSandwich)
            originalList.add(newSandwich)

            Log.d(
                "42430020",
                "Menu ditambahkan: $namaBaru"
            )

            adapter.notifyDataSetChanged()
            etInput.text.clear()

            Toast.makeText(
                this,
                "$namaBaru ditambahkan",
                Toast.LENGTH_SHORT
            ).show()
        }

    } catch (e: Exception) {

        Log.e(
            "42430020",
            "Error: ${e.message}"
        )

        Toast.makeText(
            this,
            "Terjadi kesalahan",
            Toast.LENGTH_SHORT
        ).show()
    }
    }

        // SEARCH
        btnSearch.setOnClickListener {
            val keyword =
                etSearch.text.toString().trim()
            
             Log.d(
                "42430020",
                "Mencari menu: $keyword"
            )

            // JIKA KOSONG
            if (keyword.isEmpty()) {
                dataList.clear()
                dataList.addAll(originalList)
                adapter.notifyDataSetChanged()

                return@setOnClickListener
            }

            val hasilSearch =
                ArrayList<Sandwich>()
            // LINEAR SEARCH
            for (item in originalList) {
                if (item.nama.contains(
                        keyword,
                        ignoreCase = true) )
                {
                    hasilSearch.add(item)
                }
            }

            // TAMPILKAN HASIL
            if (hasilSearch.isNotEmpty()) {
                dataList.clear()
                dataList.addAll(hasilSearch)
                adapter.notifyDataSetChanged()

            } else {
                dataList.clear()
                adapter.notifyDataSetChanged()
                Toast.makeText(
                    this,
                    "Menu tidak ditemukan",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // SORT A-Z
        btnAZ.setOnClickListener {

            Log.d(
                "42430020",
                "Sort A-Z dijalankan"
            )

            for (i in 0 until dataList.size - 1) {
                for (j in 0 until dataList.size - i - 1) {
                    if (dataList[j].nama > dataList[j + 1].nama)
                    {
                        val temp = dataList[j]
                        dataList[j] = dataList[j + 1]
                        dataList[j + 1] = temp
                    }
                }
            }

            adapter.notifyDataSetChanged()

            Toast.makeText(
                this,
                "Sort A-Z berhasil",
                Toast.LENGTH_SHORT
            ).show()
        }

        // SORT Z-A
        btnZA.setOnClickListener {

            Log.d(
                "42430020",
                "Sort Z-A dijalankan"
            )

            for (i in 0 until dataList.size - 1) {
                for (j in 0 until dataList.size - i - 1) {
                    if (dataList[j].nama < dataList[j + 1].nama)
                    {
                        val temp = dataList[j]
                        dataList[j] = dataList[j + 1]
                        dataList[j + 1] = temp
                    }
                }
            }

            adapter.notifyDataSetChanged()

            Toast.makeText(
                this,
                "Sort Z-A berhasil",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun isiDataAwal() {

        dataList.add(Sandwich(
                "CHICK KATSU SANDO",
                R.drawable.katsusando,
                "Japanese Milk Bread, Chicken Katsu, Sour Slaw, Japa Curry Mayo, Tonkatsu Sauce ",
                "60K"
            )
        )

        dataList.add(Sandwich(
                "HANGOVER HUG",
                R.drawable.hangover,
                "Ciabatta, Sunny Side Up Eggs, Crispy Bacon, Swiss Cheese, Aioli, Rockets",
                "50K"
            )
        )

        dataList.add(Sandwich(
                "MORNING AFTER MESS",
                R.drawable.morningmess,
                "Tortilla Wrap, Scrambled Eggs, Spinach, Mushroom, Cheese, Aioli",
                "48K"
            )
        )

        dataList.add(Sandwich(
                "GOLDEN CRUNCH",
                R.drawable.golden,
                "Toasted Sourdough, Melted Cheese, Chilli Relish",
                "48K"
            )
        )

        dataList.add(Sandwich(
                "THE VELVET CHAOS",
                R.drawable.velvetchaos,
                "Short Plate Beef, Hot Mustard Mayo, Trio Cheese, Roasted Pepper, Caramelised Onion, Jalapeno, Togarashi",
                "75K"
            )
        )

        dataList.add(Sandwich(
                "VIET'S BANH MI",
                R.drawable.vietbanhmi,
                "Crusty Baguette, Pickled Carrot, Roasted Chicken, Crispy Chicken Skin, Chilli Sc, Chicken Liver Pate, Herbs",
                "60K"
            )
        )

        dataList.add(Sandwich(
                "O.G CLUB",
                R.drawable.ogclub,
                "Milk Bread, Crunchy Bacon, Grilled Chicken, Boiled Egg, Tomato, Fresh Greens, Aioli",
                "85K"
            )
        )

        dataList.add(Sandwich(
                "K-BOMB CHICK BURGER",
                R.drawable.kbomb,
                "Brioche Bun, Samyang Mayo, Korean Spicy Fried Chicken, Sour Slaw",
                "65K"
            )
        )

        dataList.add(Sandwich(
                "UNHOLY CAESAR",
                R.drawable.unholy,
                "Focaccia, Chicken Cutlet, Crunchy Romaine, Caesar Dressing, Tomato",
                "65K"
            )
        )

        dataList.add(Sandwich(
                "HERCULES WRAP",
                R.drawable.hercules,
                "Pita Bread, Grilled Chicken, Grilled Chicken, Fresh Greens, Spiced Yoghurt, Pickled Onion, Cheese",
                "60K"
            )
        )
    }
}