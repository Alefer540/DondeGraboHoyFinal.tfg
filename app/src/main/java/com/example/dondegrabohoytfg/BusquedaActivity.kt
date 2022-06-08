package com.example.dondegrabohoytfg

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dondegrabohoytfg.databinding.ActivityBusquedaBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class BusquedaActivity: AppCompatActivity() {

    private lateinit var binding: ActivityBusquedaBinding
    private lateinit var database:DatabaseReference


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

            binding.botonBuscar.text="SIGUIENTE"

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

    }


    //OPCION1
    private fun readData(ciudad:String, abiertocerrado:String,espacio:String,num:Int) {
        database= FirebaseDatabase.getInstance().getReference("Localizaciones")
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$num").child("Localizacion").get().addOnSuccessListener {
            binding.tvNombre1.text=it.value.toString()
        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$num").child("Direccion").get().addOnSuccessListener {
            binding.tvDireccion.text=it.value.toString()
        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$num").child("Email").get().addOnSuccessListener {
            binding.tvEmail.text=it.value.toString()
        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$num").child("Telefono").get().addOnSuccessListener {
            binding.tvTelefono.text=it.value.toString()
        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$num").child("Imagen").get().addOnSuccessListener {
            Glide.with(this).load(it.value.toString()).into(binding.imagen)

        }


    }
    //OPCION2
   private fun readData2(ciudad:String, abiertocerrado:String,espacio:String) {
        database= FirebaseDatabase.getInstance().getReference("Localizaciones")
        val database1:DatabaseReference=database.child(ciudad)
        val database2:DatabaseReference=database1.child(abiertocerrado)
        val database3:DatabaseReference=database2.child(espacio)
        database3.get().addOnSuccessListener{
            it.children.forEach(){
                var direccion =it.child("Direccion").value
                var email =it.child("Email").value
                var nombre =it.child("Nombre").value
                var descripcion=it.child("Descripcion").value
                var telefono=it.child("Telefono").value

                println(nombre.toString())
                binding.tvNombre1.text=nombre.toString()
                binding.tvDireccion.text=direccion.toString()
                binding.tvEmail.text=email.toString()
                binding.tvTelefono.text=telefono.toString()
                binding.tvDescripcion.text=descripcion.toString()

            }


        }



    }

}




