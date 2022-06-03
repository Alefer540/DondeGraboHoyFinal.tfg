package com.example.dondegrabohoytfg


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dondegrabohoytfg.databinding.ActivityMainBinding
import com.google.android.gms.common.GooglePlayServicesNotAvailableException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mostrarCiudad: String = ""
    private var mostrarAbiertoCerrado: String = ""
    private var mostrarEspacio: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?: "")


        val abiertoCerrado = listOf("--selecciona uno--","Abierto", "Cerrado")
        val listaCiudades = listOf("--selecciona una ciudad--","Madrid","Barcelona")
        val listaEspacioCerrado= listOf("--selecciona uno--","Apartamento","Bar","Chalet","Centro de culto","Discoteca","Hotel","Museo","Restaurante")
        val listaEspacioAbierto= listOf("--selecciona uno--","Azotea","Avenida","Plaza","Polideportivo/piscina","Parque")

        val adaptador: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.spinner_personalizado, listaCiudades)
        binding.spinnerCiudad.adapter = adaptador
        val adaptador2: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.spinner_personalizado, abiertoCerrado)
        binding.spinnerAbiertoCerrado.adapter = adaptador2
        val adaptador3: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.spinner_personalizado, listaEspacioAbierto)
        binding.spinnerEspacioAbierto.adapter = adaptador3
        val adaptador4: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.spinner_personalizado, listaEspacioCerrado)
        binding.spinnerEspacioCerrado.adapter = adaptador4

        binding.spinnerCiudad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    this@MainActivity,
                    "Seleccione uno para continuar",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onItemSelected(p0: AdapterView<*>, p1: View?, clickado1: Int, p3: Long) {

                if (clickado1 == 0) {

                }
                if (clickado1 == 1) {
                    mostrarCiudad = "Madrid"

                }
                if (clickado1 == 2) {
                    mostrarCiudad = "Barcelona"

                }

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

                override fun onItemSelected(p0: AdapterView<*>, p1: View?, clickado: Int, p3: Long
                ) {

                    if (clickado == 0) {
                        binding.spinnerEspacioAbierto.visibility = View.GONE
                        binding.spinnerEspacioCerrado.visibility = View.GONE
                    }

                    if (clickado == 1){
                        mostrarAbiertoCerrado = "Abierto"

                        binding.spinnerEspacioAbierto.visibility = View.VISIBLE
                        binding.spinnerEspacioCerrado.visibility = View.GONE

                    }
                    if (clickado == 2){
                        mostrarAbiertoCerrado = "Cerrado"

                        binding.spinnerEspacioCerrado.visibility = View.VISIBLE
                        binding.spinnerEspacioAbierto.visibility = View.GONE

                    }

                }

            }
        binding.spinnerEspacioAbierto.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    this@MainActivity,
                    "Seleccione uno para continuar",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onItemSelected(p0: AdapterView<*>, p1: View?, clickado2: Int, p3: Long) {
                if (clickado2 == 0) {

                }

                if (clickado2 == 1) {
                    //mostar = getSpinnersSelections()
                    mostrarEspacio = "Azotea"

                }
                if (clickado2 == 2) {
                    mostrarEspacio = "Avenida"

                }
                if (clickado2 == 3) {
                    mostrarEspacio = "Plaza"

                }
                if (clickado2 == 4) {
                    mostrarEspacio = "Polideportivo/Piscina"

                }
                if (clickado2 == 5) {
                    mostrarEspacio = "Parque"

                }
            }

        }
        binding.spinnerEspacioCerrado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    this@MainActivity,
                    "Seleccione uno para continuar",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onItemSelected(p0: AdapterView<*>, p1: View?, clickado3: Int, p3: Long) {

                if (clickado3 == 0) {

                }

                if (clickado3 == 1) {

                    mostrarEspacio = "Apartamento"

                }
                if (clickado3 == 2) {
                    mostrarEspacio = "Bar"

                }
                if (clickado3 == 3) {
                    mostrarEspacio = "Chalet"

                }
                if (clickado3 == 4) {
                    mostrarEspacio = "Centro de culto"

                }
                if (clickado3 == 4) {
                    mostrarEspacio = "Discoteca"

                }
                if (clickado3 == 5) {
                    mostrarEspacio = "Hotel"

                }
                if (clickado3 == 6) {
                    mostrarEspacio = "Museo"

                }
                if (clickado3 == 7) {
                    mostrarEspacio = "Restaurante"

                }



            }


        }
        binding.botonBuscar.setOnClickListener {


            val bundle = Bundle()
            bundle.putString("Ciudad", mostrarCiudad)
            bundle.putString("Espacio", mostrarEspacio)
            bundle.putString("AbiertoCerrado", mostrarAbiertoCerrado)

            val Busquedaintent = Intent(this, BusquedaActivity::class.java)
            Busquedaintent.putExtras(bundle)
            startActivity(Busquedaintent)



        }
    }




    private fun setup(email:String){

        binding.tvNombreTextView.text=email
    }


}














