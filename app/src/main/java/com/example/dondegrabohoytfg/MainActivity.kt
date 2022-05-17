package com.example.dondegrabohoytfg

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.dondegrabohoytfg.databinding.ActivityMainBinding
import java.util.*

 class MainActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMainBinding

     companion object {
         fun launch(context: Context) {
             val intent = Intent(context, MainActivity::class.java)
             context.startActivity(intent)

         }
     }

     private var spCiudad:Spinner?=null
     private var spabiertoCerrado:Spinner?=null
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(R.layout.activity_main)

         spCiudad=findViewById(R.id.spinnerCiudad)
         spabiertoCerrado = findViewById(R.id.spinnerAbiertoCerrado)
         val abiertoCerrado= arrayOf("abierto","Cerrado")
         val listaCiudades= arrayOf("Madrid","Toledo","Sevilla","Barcelona")
         var adaptador:ArrayAdapter<String> = ArrayAdapter(this,R.layout.spinner_personalizado,listaCiudades)
         spCiudad?.adapter=adaptador
         var adaptador2:ArrayAdapter<String> = ArrayAdapter(this,R.layout.spinner_personalizado,abiertoCerrado)
         spabiertoCerrado?.adapter=adaptador2



         }
     }





