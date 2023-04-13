package pt.ua.clima

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import pt.ua.clima.databinding.ActivityLogin2Binding

class Login : AppCompatActivity() {
    private lateinit var  binding:ActivityLogin2Binding
    private  auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(R.layout.activity_login2)

        binding.btnSubmit.setOnClickListener{
            val email = binding.editEmail.text.toString()
            val senha = binding.etPassword.text.toString()

            if(email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha todos os campos!",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else{
                auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener{autenticacao ->
                    if (autenticacao.isSuccessfull){
                        navegarToHome()
                    }
                }.addOnFailureListener{
                    val snackbar = Snackbar.make(view,"Erro ao fazer o login do usuário!",Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
                }
            }
        }

        binding.btnRegist.setOnClickListener(){
            val intent = Intent(this,Registro::class.java)
            startActivity(intent)
            finish()
        }
    }

    private  fun navegarToHome(){
        val intent = Intent(this,Home::class.java)
        startActivity(intent)
        finish()
    }
}