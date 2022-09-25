package com.example.w2s3_0706012110012

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.w2s3_0706012110012.Adapter.RVAdapter
import com.example.w2s3_0706012110012.Data.GlobalVar
import com.example.w2s3_0706012110012.Interface.CardListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CardListener {
    private val adapter = RVAdapter(GlobalVar.listDataAnimal, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        listener()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun listener() {
        tambahAnimal.setOnClickListener {
            val myIntent = Intent(this, CreateActivity::class.java)

            startActivity(myIntent)
        }
    }

    private fun setUpRecyclerView(){
        val layoutManager = GridLayoutManager(baseContext, 1)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter= adapter
    }

    override fun onEditClick(position: Int) {
        val thisIntent = Intent(this, CreateActivity::class.java).apply {
            putExtra("Position", position)
        }
        startActivity(thisIntent)
    }

    override fun onDeleteClick(position: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Hapus Hewan")
            .setMessage("Apakah anda ingin menghapus hewan ini?")
            .setNegativeButton("Batal") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("Setuju") { dialog, which ->
                GlobalVar.listDataAnimal.removeAt(position)
                Toast.makeText(applicationContext, "Data berhasil di hapus", Toast.LENGTH_LONG).show()
                onResume()
            }
            .show()
    }
}