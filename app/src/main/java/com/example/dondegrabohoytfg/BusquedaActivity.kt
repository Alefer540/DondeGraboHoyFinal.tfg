package com.example.dondegrabohoytfg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dondegrabohoytfg.databinding.ActivityBusquedaBinding
import com.example.dondegrabohoytfg.databinding.ActivityMainBinding


class BusquedaActivity: AppCompatActivity() {

    private lateinit var binding: ActivityBusquedaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusquedaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}