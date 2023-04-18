package pt.ua.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import pt.ua.clima.databinding.ActivityProfileBinding
import pt.ua.clima.databinding.ActivityRegistroBinding

class Profile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val voltarToLogin = Intent(this,Login::class.java)
            startActivity(voltarToLogin)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val userId = FirebaseAuth.getInstance().currentUser.providerId

    }
}