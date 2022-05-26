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
import com.google.android.gms.common.util.ArrayUtils.contains
import java.util.*

 class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding

     //companion object {
         //fun launch(context: Context) {
            // val intent = Intent(context, MainActivity::class.java)
             //context.startActivity(intent)

         //}
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(R.layout.activity_main)

         val bundle=intent.extras
         val email=bundle?.getString("email")
         setup(email ?:"")


          val spCiudad=findViewById<Spinner>(R.id.spinnerCiudad)
         val spabiertoCerrado = findViewById<Spinner>(R.id.spinnerAbiertoCerrado)

         val abiertoCerrado= listOf("-- Seleccione uno --","Abierto","Cerrado")
         val listaCiudades= listOf("-- Seleccione una ciudad --","Madrid","Toledo","Sevilla","Barcelona")

         val adaptador:ArrayAdapter<String> = ArrayAdapter(this,R.layout.spinner_personalizado,listaCiudades)
         spCiudad.adapter=adaptador
         val adaptador2:ArrayAdapter<String> = ArrayAdapter(this,R.layout.spinner_personalizado,abiertoCerrado)
         spabiertoCerrado.adapter=adaptador2



         spCiudad.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{

             override fun onNothingSelected(p0: AdapterView<*>?) {
                 Toast.makeText(this@MainActivity,"Seleccione uno para continuar",Toast.LENGTH_LONG).show()
             }
             override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                 Toast.makeText(this@MainActivity,"Has seleccionado: "+listaCiudades[p2],Toast.LENGTH_LONG).show()


             }

         }
         spabiertoCerrado.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{

             override fun onNothingSelected(p0: AdapterView<*>?) {
                 Toast.makeText(this@MainActivity,"Seleccione uno para continuar",Toast.LENGTH_LONG).show()
             }
             override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                 Toast.makeText(this@MainActivity,"Has seleccionado: "+abiertoCerrado[p2],Toast.LENGTH_LONG).show()
                    println(p2)
                 if (p2 ==0){
                     binding.linearEspacioAbierto.visibility=View.GONE
                 }
                 if(p2 == 1){
                     println("aaaaaaaaaaaaaa" +p2)
                     println("holaaaaa")
                     binding.linearEspacioAbierto.visibility=View.VISIBLE
                 }
                 if(p2 == 2)
                     binding.linearEspacioAbierto.visibility=View.GONE
             }

         }

         }
     private fun setup(email:String){

         binding.tvNombreTextView.text=email
     }


     }








