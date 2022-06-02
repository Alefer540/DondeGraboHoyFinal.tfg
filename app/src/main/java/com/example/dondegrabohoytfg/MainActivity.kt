package com.example.dondegrabohoytfg

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.dondegrabohoytfg.databinding.ActivityMainBinding
import com.google.android.gms.common.util.ArrayUtils.contains
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?: "")


        val abiertoCerrado = listOf("-- Seleccione uno --", "Abierto", "Cerrado")
        val listaCiudades =
            listOf("-- Seleccione una ciudad --", "Madrid", "Toledo", "Sevilla", "Barcelona")

        val adaptador: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.spinner_personalizado, listaCiudades)
        binding.spinnerCiudad.adapter = adaptador
        val adaptador2: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.spinner_personalizado, abiertoCerrado)
        binding.spinnerAbiertoCerrado.adapter = adaptador2


        binding.spinnerCiudad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    this@MainActivity,
                    "Seleccione uno para continuar",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onItemSelected(p0: AdapterView<*>, p1: View?, clickeado: Int, p3: Long) {
                Toast.makeText(
                    this@MainActivity,
                    "Has seleccionado: " + listaCiudades[clickeado],
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        binding.spinnerAbiertoCerrado.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(
                        this@MainActivity,
                        "Seleccione uno para continuar",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onItemSelected(
                    p0: AdapterView<*>,
                    p1: View?,
                    clickado: Int,
                    p3: Long
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        "Has seleccionado: " + abiertoCerrado[clickado],
                        Toast.LENGTH_LONG
                    ).show()

                    if (clickado == 0) {
                        binding.linearEspacioAbierto.visibility = View.GONE
                        binding.linearEspacioCerrado.visibility = View.GONE
                    }
                    if (clickado == 1) {
                        binding.linearEspacioAbierto.visibility = View.VISIBLE
                        binding.linearEspacioCerrado.visibility = View.GONE
                    }
                    if (clickado == 2) {
                        binding.linearEspacioCerrado.visibility = View.VISIBLE
                        binding.linearEspacioAbierto.visibility = View.GONE

                    }

                }

            }
        binding.botonBuscar.setOnClickListener{
            showMainActivity()
        }
    }
    private fun showMainActivity (){

        val mainIntent=Intent(this,MainActivity::class.java).apply {

        }
        startActivity(mainIntent)

    }
    private fun setup(email:String){

        binding.tvNombreTextView.text=email
    }


}








