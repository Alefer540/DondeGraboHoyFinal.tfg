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
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(R.layout.activity_main)

         spCiudad=findViewById(R.id.spinnerCiudad)

         val listaCiudades= arrayOf("Selecciona una ciudad","Madrid","Toledo")
         var adaptador:ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_spinner_item,listaCiudades)
         spCiudad?.adapter=adaptador
         spCiudad?.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
             override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                 TODO("Not yet implemented")
             }

             override fun onNothingSelected(p0: AdapterView<*>?) {
                 TODO("Not yet implemented")
             }

         }
     }
 }




