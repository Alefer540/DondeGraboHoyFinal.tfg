package com.example.dondegrabohoytfg

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dondegrabohoytfg.databinding.ActivityBusquedaBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.view.View
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase



class BusquedaActivity: AppCompatActivity() {

    private lateinit var binding: ActivityBusquedaBinding
    private lateinit var database:DatabaseReference
    //var url="http://maps.google.com/maps?daddr="
    var url="https://www.google.com/maps/place/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusquedaBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val bundle = intent.extras
        val Ciudad = bundle?.getString("Ciudad")
        val Espacio = bundle?.getString("Espacio")
        val AbiertoCerrado = bundle?.getString("AbiertoCerrado")
        var num=0

        binding.tvAoC.text=AbiertoCerrado
        binding.tvCiudad.text=Ciudad
        binding.tvTipo.text=Espacio
        println(Ciudad)
        println(Espacio)


        binding.botonBuscar.setOnClickListener {

            binding.botonBuscar.text="x"
            binding.botonVolver.visibility= android.view.View.VISIBLE
            binding.mostrardatos.visibility= android.view.View.VISIBLE
            binding.linearbusqueda.visibility=android.view.View.GONE

            if (Ciudad != null){
                if (AbiertoCerrado != null) {
                    if (Espacio != null) {
                        if ( Ciudad.isNotEmpty() && AbiertoCerrado.isNotEmpty()&& Espacio.isNotEmpty()){
                                readData(Ciudad,AbiertoCerrado,Espacio,num)
                            num++
                            if(num > 1){
                                num=0
                            }

                            }
                        }
                    }
                }
             }

        //volver al menu de seleccion
        binding.botonVolver.setOnClickListener {
            val volverIntent = Intent(this, MainActivity::class.java)

            startActivity(volverIntent)
        }
        binding.botonMaps.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)

        }

    }


    //OPCION1
    private fun readData(ciudad:String, abiertocerrado:String,espacio:String,num:Int) {
        database= FirebaseDatabase.getInstance().getReference("Localizaciones")
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$num").child("Localizacion").get().addOnSuccessListener {
            binding.tvLocalizacion.text=it.value.toString()
        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$num").child("Direccion").get().addOnSuccessListener {
            url="https://www.google.com/maps/place/"
            binding.tvDireccion.text=it.value.toString()
            url+=it.value.toString()
        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$num").child("Nombre").get().addOnSuccessListener {
            binding.tvNombreAnfitrion.text=it.value.toString()
        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$num").child("Email").get().addOnSuccessListener {
            binding.tvEmail.text=it.value.toString()
        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$num").child("Telefono").get().addOnSuccessListener {
            binding.tvTelf.text=it.value.toString()
        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$num").child("Descripcion").get().addOnSuccessListener {
            binding.tvDesc.text=it.value.toString()
        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$num").child("Imagen").get().addOnSuccessListener {
            Glide.with(this).load(it.value.toString()).into(binding.foto)

        }


    }

}




