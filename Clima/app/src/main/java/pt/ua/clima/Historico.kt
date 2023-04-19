package pt.ua.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import pt.ua.clima.databinding.ActivityHistoricoBinding

class Historico : AppCompatActivity() {
    private lateinit var  binding: ActivityHistoricoBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.perfil.setOnClickListener {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
            finish()
        }
        binding.definicoes.setOnClickListener {
            val intent = Intent(this,Definitions::class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        db.collection("measurement").document(uid.toString())
            .addSnapshotListener {documento, error ->
                if(documento != null){
                    binding.varTemp.text = documento.getDouble("temperature").toString()
                    binding.varHumi.text = documento.getDouble("humidity").toString()
                    binding.varPressAt.text = documento.getDouble("pressure").toString()
                    binding.varLum.text = documento.getDouble("luminosity").toString()
                }
            }

    }
}