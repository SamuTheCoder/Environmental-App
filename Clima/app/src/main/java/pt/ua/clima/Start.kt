package pt.ua.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pt.ua.clima.databinding.ActivityProfileBinding
import pt.ua.clima.databinding.ActivityStartBinding

class Start : AppCompatActivity() {
    private  lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var i = 1000
        while (i > 0){
            --i
        }
        navegarToHome()

    }
    private  fun navegarToHome(){
        val intent = Intent(this,Home::class.java)
        startActivity(intent)
        finish()
    }
}