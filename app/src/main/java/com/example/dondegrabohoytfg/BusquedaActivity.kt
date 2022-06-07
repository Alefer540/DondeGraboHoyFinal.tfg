package com.example.dondegrabohoytfg

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dondegrabohoytfg.databinding.ActivityBusquedaBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class BusquedaActivity: AppCompatActivity() {

    private lateinit var binding: ActivityBusquedaBinding
    private lateinit var database:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusquedaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database= FirebaseDatabase.getInstance().getReference("Localizaciones")

        val bundle = intent.extras
        val Ciudad = bundle?.getString("Ciudad")
        val Espacio = bundle?.getString("Espacio")
        val AbiertoCerrado = bundle?.getString("AbiertoCerrado")

        binding.tvAoC.text=AbiertoCerrado
        binding.tvCiudad.text=Ciudad
        binding.tvTipo.text=Espacio
        println(Ciudad)
        println(Espacio)

        //mostrar datos BD
        //APARECEN TODOS NULL
        database= FirebaseDatabase.getInstance().getReference()
        //if (Ciudad != null) {
            //if (AbiertoCerrado != null) {
               // if (Espacio != null) {
                   // database.child(Ciudad).child(AbiertoCerrado).child(Espacio).get().addOnSuccessListener {
                       // val Direccion=it.child("Email").value
                        //val Nombre=it.child("Nombre").value

                        //binding.tvDireccion.text= Direccion.toString()
                        //binding.tvNombre.text= Nombre.toString()
                    //}
                //}
            //}
        //}








        //volver al menu de seleccion
        binding.botonVolver.setOnClickListener {
            val volverIntent = Intent(this, MainActivity::class.java)

            startActivity(volverIntent)
        }

    }
}




