package com.example.pakdananuas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var etInput: EditText
    private lateinit var btnAdd: Button

    private lateinit var dataList: ArrayList<Sandwich>
    private lateinit var adapter: SandwichAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. HUBUNGKAN KOMPONEN UI
        gridView = findViewById(R.id.gridView)
        etSearch = findViewById(R.id.etSearch)
        btnSearch = findViewById(R.id.btnSearch)
        etInput = findViewById(R.id.etInput)
        btnAdd = findViewById(R.id.btnAdd)

        // 2. INISIALISASI DATA
        dataList = ArrayList()
        isiDataAwal()

        // 3. SET ADAPTER
        adapter = SandwichAdapter(this, dataList)
        gridView.adapter = adapter

        // 4. NAVIGASI KE DETAIL (INTENT)
        gridView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetailActivity::class.java)
            val selectedSandwich = dataList[position]
            
            intent.putExtra("nama", selectedSandwich.nama)
            intent.putExtra("gambar", selectedSandwich.gambar)
            intent.putExtra("deskripsi", selectedSandwich.deskripsi)
            intent.putExtra("harga", selectedSandwich.harga)
            startActivity(intent)
        }

        // 5. VALIDASI INPUT TAMBAH
        btnAdd.setOnClickListener {
            val namaBaru = etInput.text.toString().trim()
            
            if (namaBaru.isEmpty()) {
                // Pesan Error jika kosong
                etInput.error = "Nama sandwich tidak boleh kosong!"
                Toast.makeText(this, "Silakan isi nama menu", Toast.LENGTH_SHORT).show()
            } else {
                // Tambah ke list jika valid
                dataList.add(Sandwich(namaBaru, R.drawable.sandwich, "Menu Baru", "Price TBD"))
                adapter.notifyDataSetChanged()
                etInput.text.clear()
                Toast.makeText(this, "$namaBaru ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }

        // 6. VALIDASI SEARCH
        btnSearch.setOnClickListener {
            val keyword = etSearch.text.toString().trim()
            if (keyword.isEmpty()) {
                etSearch.error = "Masukkan kata kunci!"
            } else {
                Toast.makeText(this, "Mencari: $keyword", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isiDataAwal() {
        dataList.add(Sandwich("CHICK KATSU SANDO", R.drawable.katsusando, "Japanese Milk Bread, Chicken Katsu...", "60K"))
        dataList.add(Sandwich("HANGOVER HUG", R.drawable.hangover, "Ciabatta, Sunny Side Up Eggs...", "50K"))
        dataList.add(Sandwich("MORNING AFTER MESS", R.drawable.morningmess, "Tortilla Wrap, Scrambled Eggs...", "48K"))
        dataList.add(Sandwich("GOLDEN CRUNCH", R.drawable.golden, "Toasted Sourdough, Melted Cheese...", "48K"))
        dataList.add(Sandwich("THE VELVET CHAOS", R.drawable.velvetchaos, "Short Plate Beef, Hot Mustard...", "75K"))
        dataList.add(Sandwich("VIET'S BANH MI", R.drawable.vietbanhmi, "Baguette, Pickled Carrot...", "60K"))
        dataList.add(Sandwich("O.G CLUB", R.drawable.ogclub, "Milk Bread, Crunchy Bacon...", "85K"))
        dataList.add(Sandwich("K-BOMB CHICK BURGER", R.drawable.kbomb, "Brioche Bun, Samyang Mayo...", "65K"))
        dataList.add(Sandwich("UNHOLY CAESAR", R.drawable.unholy, "Focaccia, Chicken Cutlet...", "65K"))
        dataList.add(Sandwich("HERCULES WRAP", R.drawable.hercules, "Pita Bread, Grilled Chicken...", "60K"))
    }
}
