package com.example.dondegrabohoytfg

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dondegrabohoytfg.databinding.ActivityBusquedaBinding



class BusquedaActivity: AppCompatActivity() {

    private lateinit var binding: ActivityBusquedaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusquedaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val Ciudad = bundle?.getString("Ciudad")
        val Espacio = bundle?.getString("Espacio")
        val AbiertoCerrado = bundle?.getString("AbiertoCerrado")



        binding.tvAoC.text=AbiertoCerrado
        binding.tvCiudad.text=Ciudad
        binding.tvTipo.text=Espacio




        binding.botonVolver.setOnClickListener {
            val volverIntent = Intent(this, MainActivity::class.java)

            startActivity(volverIntent)
        }

    }
}
