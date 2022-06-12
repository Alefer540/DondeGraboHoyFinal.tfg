package com.example.dondegrabohoytfg

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.dondegrabohoytfg.databinding.ActivityResultadoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class ResultadoActivity: AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding
    private lateinit var database:DatabaseReference
    //var url="http://maps.google.com/maps?daddr="
    var url="https://www.google.com/maps/place/"
    private var number = ""
    private var correoelectrinico = ""
    private val requestCall = 1
    private var reset=true
    private var idNum=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val bundle = intent.extras
        val ciudad = bundle?.getString("Ciudad")
        val espacio = bundle?.getString("Espacio")
        val abiertoCerrado = bundle?.getString("AbiertoCerrado")



        binding.tvAoC.text=abiertoCerrado
        binding.tvCiudad.text=ciudad
        binding.tvTipo.text=espacio
        println(ciudad)
        println(espacio)

            binding.mostrardatos.visibility= android.view.View.VISIBLE

            if (ciudad != null){
                if (abiertoCerrado != null) {
                    if (espacio != null) {
                        if ( ciudad.isNotEmpty() && abiertoCerrado.isNotEmpty()&& espacio.isNotEmpty()){
                                readData(ciudad,abiertoCerrado,espacio,idNum)
                            if (reset){
                                idNum++
                            }else{
                                idNum = 0
                            }


                        }
                        }
                    }
                }


        //volver al menu de seleccion
        binding.volver.setOnClickListener {
            val volverIntent = Intent(this, BusquedaActivity::class.java)

            startActivity(volverIntent)
        }
        //entrar a maps con busqueda en dirección
        binding.maps.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        binding.llamar.setOnClickListener{
            makePhoneCall()

        }
        //enviar email
        binding.email.setOnClickListener{
            val emailIntent=Intent(Intent.ACTION_SENDTO,
            Uri.fromParts("mailto",correoelectrinico,null))
            startActivity(Intent.createChooser(emailIntent,"Enviando email"))

        }
        binding.botonBuscar.setOnClickListener {
            if (ciudad != null){
                if (abiertoCerrado != null) {
                    if (espacio != null) {
                        if ( ciudad.isNotEmpty() && abiertoCerrado.isNotEmpty()&& espacio.isNotEmpty()){
                            readData(ciudad,abiertoCerrado,espacio,idNum)
                            idNum++
                            if(idNum > 1){
                                idNum=0
                            }

                        }
                    }
                }
            }

        }

    }


    //LLAMADA A LA BASE DE DATOS
    private fun readData(ciudad:String, abiertocerrado:String,espacio:String,idnum:Int) {
        database= FirebaseDatabase.getInstance().getReference("Localizaciones")
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$idnum").child("Localizacion").get().addOnSuccessListener {
            if(it.value==null){
                reset=false
                binding.mostrardatos.callOnClick()
            }else{
                reset=true
                binding.tvLocalizacion.text=it.value.toString()
            }
        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$idnum").child("Direccion").get().addOnSuccessListener {
            if(it.value==null){
                reset=false
                binding.mostrardatos.callOnClick()
            }else{
                reset=true
                url="https://www.google.com/maps/place/"
                binding.tvDireccion.text=it.value.toString()
                url+=it.value.toString()
            }
        }

        database.child(ciudad).child(abiertocerrado).child(espacio).child("$idnum").child("Nombre").get().addOnSuccessListener {
            if(it.value==null){
                reset=false
                binding.mostrardatos.callOnClick()
            }else{
                reset=true
                binding.tvNombreAnfitrion.text=it.value.toString()
            }

        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$idnum").child("Email").get().addOnSuccessListener {
            if (it.value == null) {
                reset = false
                binding.mostrardatos.callOnClick()
            } else {
                reset = true
                binding.tvEmail.text = it.value.toString()
                correoelectrinico = it.value.toString()
            }
        }

        database.child(ciudad).child(abiertocerrado).child(espacio).child("$idnum").child("Telefono").get().addOnSuccessListener {
            if (it.value == null) {
                reset = false
                binding.mostrardatos.callOnClick()
            } else {
                reset = true
                binding.tvTelf.text=it.value.toString()
                number=it.value.toString()
            }

        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$idnum").child("Descripcion").get().addOnSuccessListener {
            if (it.value == null) {
                reset = false
                binding.mostrardatos.callOnClick()
            } else {
                reset = true
                binding.tvDesc.text=it.value.toString()
            }

        }
        database.child(ciudad).child(abiertocerrado).child(espacio).child("$idnum").child("Imagen").get().addOnSuccessListener {
            Glide.with(this).load(it.value.toString()).into(binding.foto)
            if (it.value == null) {
                reset = false
                binding.mostrardatos.callOnClick()
            } else {
                reset = true
                Glide.with(this).load(it.value.toString()).into(binding.foto)
            }

        }

    }
    //LLAMAR POR TELEFONO
    private fun makePhoneCall() {
        if (number.trim { it <= ' ' }.isNotEmpty()) {
            if (ContextCompat.checkSelfPermission(
                    this@ResultadoActivity,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@ResultadoActivity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    requestCall
                )
            } else {
                val dial = "tel:$number"
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            }
        } else {
            Toast.makeText(this@ResultadoActivity, "Enter Phone Number", Toast.LENGTH_SHORT).show()
        }
    }
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {

        //Para las llamadas
        if (requestCode == requestCall) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall()
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }

}




