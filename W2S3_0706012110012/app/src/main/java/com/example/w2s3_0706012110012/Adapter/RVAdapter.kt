package com.example.w2s3_0706012110012.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.w2s3_0706012110012.Interface.CardListener
import com.example.w2s3_0706012110012.Model.Animals
import com.example.w2s3_0706012110012.Model.Ayam
import com.example.w2s3_0706012110012.Model.Sapi
import com.example.w2s3_0706012110012.R
import kotlinx.android.synthetic.main.animal_card.view.*

class RVAdapter(var listAnimal: ArrayList<Animals>, val cardListener: CardListener):
    RecyclerView.Adapter<RVAdapter.viewHolder>() {
    class viewHolder(itemView: View, val cardListener1: CardListener) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(data: Animals) {
            itemView.namaHewan.text = data.name

            if (data is Sapi) {
                itemView.jenisHewan.text = "Sapi"
            } else if (data is Ayam) {
                itemView.jenisHewan.text = "Ayam"
            } else {
                itemView.jenisHewan.text = "Kambing"
            }

            itemView.usiaHewan.text = "Usia : " + data.age.toString() + " Bulan"

            if (data.imageUri.isNotEmpty()) {
                itemView.imageCardHewan.setImageURI(Uri.parse(data.imageUri))
            }

            itemView.editButton.setOnClickListener {
                cardListener1.onEditClick(adapterPosition)
            }

            itemView.deleteButton.setOnClickListener {
                cardListener1.onDeleteClick(adapterPosition)

            }

            itemView.soundBtn.setOnClickListener {
                Toast.makeText(itemView.context, data.interaction(), Toast.LENGTH_SHORT).show()
            }

            itemView.feedBtn.setOnClickListener {
                Toast.makeText(itemView.context, data.feed(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.animal_card, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listAnimal[position])
    }

    override fun getItemCount(): Int {
        return listAnimal.size
    }
}

