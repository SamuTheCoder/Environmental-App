package pt.ua.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import pt.ua.clima.databinding.ActivityProfileBinding
import pt.ua.clima.databinding.ActivityRegistroBinding

class Profile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val db = FirebaseFirestore.getInstance()
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

        binding.HomeTela.setOnClickListener{
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }

        binding.pesquisar.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
            finish()
        }

        binding.btHistorico.setOnClickListener {
            val intent = Intent(this,Historico::class.java)
            startActivity(intent)
            finish()
        }

        binding.definitionsButtonD.setOnClickListener {
            val intent = Intent(this, Definitions::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        //val userId = FirebaseAuth.getInstance().currentUser.providerId
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        db.collection("Users").document(uid.toString())
            .addSnapshotListener {documento, error ->
                if(documento != null){
                   binding.textNomeUsuario.text = documento.getString("Nome") + " "  +  documento.getString("Ultimo_Nome")
                   binding.textEmailUser.text   = documento.getString("email")
                }
            }

        }

}