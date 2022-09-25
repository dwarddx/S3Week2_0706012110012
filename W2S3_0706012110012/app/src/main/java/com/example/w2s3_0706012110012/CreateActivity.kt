package com.example.w2s3_0706012110012

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.w2s3_0706012110012.Data.GlobalVar
import com.example.w2s3_0706012110012.Model.Animals
import com.example.w2s3_0706012110012.Model.Ayam
import com.example.w2s3_0706012110012.Model.Kambing
import com.example.w2s3_0706012110012.Model.Sapi
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    private var position = -1
    private lateinit var urii: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_create)

        urii = ""

        Listener()
        GetIntent()
        Display()
    }


    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data
            urii = uri.toString()// GET PATH TO IMAGE FROM GALLEY

            imageButton2.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
        }
    }

    private fun Listener() {
        ErrorHandle.visibility = View.GONE

        imageButton2.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        backButton.setOnClickListener {
            finish()
        }

        buttonS.setOnClickListener {
            var isCompleted = true

            if (animalName.editText?.text.toString().trim() == "") {
                isCompleted = false
                animalName.error = "Input nama hewan"
            } else {
                animalName.error = ""
            }

            if (animalType.checkedRadioButtonId == -1) {
                isCompleted = false
                ErrorHandle.visibility = View.VISIBLE
            } else {
                ErrorHandle.visibility = View.GONE
            }

            if (animalAge.editText?.text.toString().trim() == "") {
                isCompleted = false
                animalAge.error = "Input usia hewan"
            } else {
                animalAge.error = ""
            }

            if (isCompleted) {
                val animal: Animals

                if (animalType.checkedRadioButtonId == R.id.radioButton5) {
                    animal = Sapi(animalName.editText?.text.toString().trim(), animalAge.editText?.text.toString().trim().toInt(), "")
                } else if (animalType.checkedRadioButtonId == R.id.radioButton6){
                    animal = Ayam(animalName.editText?.text.toString().trim(), animalAge.editText?.text.toString().trim().toInt(), "")
                } else {
                    animal = Kambing(animalName.editText?.text.toString().trim(), animalAge.editText?.text.toString().trim().toInt(), "")
                }

                if (position == -1) {
                    GlobalVar.listDataAnimal.add(animal)

                    val index = GlobalVar.listDataAnimal.size - 1

                    if (urii.isNotEmpty()) {
                        GlobalVar.listDataAnimal[index].imageUri = urii
                    }

                    Toast.makeText(applicationContext, "Berhasil menambahkan data hewan", Toast.LENGTH_SHORT).show()
                } else {
                    animal.imageUri = GlobalVar.listDataAnimal[position].imageUri
                    GlobalVar.listDataAnimal.set(position, animal)

                    if (urii.isNotEmpty()) {
                        GlobalVar.listDataAnimal[position].imageUri = urii
                    }

                    Toast.makeText(applicationContext, "Berhasil memperbarui data hewan", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        }
    }

    private fun GetIntent() {
        position = intent.getIntExtra("Position", -1)
    }

    private fun Display() {
        if (position != -1) {
            animalName.editText!!.setText(GlobalVar.listDataAnimal.get(position).name)

            if (GlobalVar.listDataAnimal.get(position) is Sapi) {
                animalType.check(R.id.radioButton5)
            } else if (GlobalVar.listDataAnimal.get(position) is Ayam) {
                animalType.check(R.id.radioButton6)
            } else {
                animalType.check(R.id.radioButton7)
            }

            animalAge.editText!!.setText(GlobalVar.listDataAnimal.get(position).age.toString())

            if (GlobalVar.listDataAnimal.get(position).imageUri != "") {
                imageButton2.setImageURI(
                    Uri.parse(GlobalVar.listDataAnimal.get(position).imageUri)
                )
            }
            createUpHewan.text = "Edit Hewan"
        }
    }
}