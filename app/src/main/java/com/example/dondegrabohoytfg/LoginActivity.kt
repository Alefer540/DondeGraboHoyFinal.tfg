package com.example.dondegrabohoytfg


import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.dondegrabohoytfg.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val sliderInicio= mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Slider de imagenes
        val carousel: ImageCarousel = findViewById(R.id.carousel)
        sliderInicio.add(CarouselItem("https://cdn0.bodas.net/vendor/2021/3_2/960/jpg/lacasademonico-fincabodasmadrid-38_1_2021-164250074628859.jpeg"))
        sliderInicio.add(CarouselItem("https://www.vopi4.com/archivos/piscina-salts-montjuic-02_030517200108.jpg"))
        sliderInicio.add(CarouselItem("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0b/74/e3/06/elrow-fabrik-carnaval.jpg?w=1200&h=-1&s=1"))
        carousel.addData(sliderInicio)
        //union entre activities

        binding.lUsu.doAfterTextChanged {
            comprobartexto()
        }
        binding.lPass.doAfterTextChanged {
            comprobartexto()
        }
        binding.bContinue.setOnClickListener {

            if (binding.lUsu.text.isNotEmpty() && binding.lPass.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        binding.lUsu.text.toString(),
                        binding.lPass.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful){
                            showMainActivity(it.result?.user?.email?: "")
                        }else{
                            showAlert()
                        }
                    }
            }
        }

    }

    private fun showAlert() {
        val builder=AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("El usuario no se encuentra registrado dentro de la app contacte con el servicio tecnico")
        builder.setPositiveButton("Salir",null)
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }
    private fun showMainActivity (email:String){

        val mainIntent=Intent(this,MainActivity::class.java).apply {
            putExtra("email",email)
        }
        startActivity(mainIntent)

    }


    fun comprobartexto(){
        if(binding.lUsu.text.length>3 && binding.lPass.text.length==6 ){
            binding.bContinue.visibility= View.VISIBLE
        }else{
            binding.bContinue.visibility= View.GONE
        }
    }
}

