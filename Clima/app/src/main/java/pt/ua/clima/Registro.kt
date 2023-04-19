package pt.ua.clima

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import pt.ua.clima.databinding.ActivityLogin2Binding
import pt.ua.clima.databinding.ActivityRegistroBinding

class Registro : AppCompatActivity() {
    private lateinit var  binding: ActivityRegistroBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegist.setOnClickListener{view ->
            val  name = binding.PriNome.text.toString()
            val lastName = binding.ultNome.text.toString()
            val email = binding.editEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confPass = binding.ConfPassword.text.toString()

            if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confPass.isEmpty()){
                val  snackbar = Snackbar.make(view,"preencha todos os campos!",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }
            else{
                if (!(confPass?.equals(password)?:(password === null))){
                    val  snackbar = Snackbar.make(view,"Senhas diferentes!",Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
                }
                else{
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{cadastro ->
                        if (cadastro.isSuccessful){
                            val  snackbar = Snackbar.make(view,"Sucesso ao cadastrar o Usuario!",Snackbar.LENGTH_SHORT)
                            snackbar.setBackgroundTint(Color.GREEN)
                            snackbar.show()
                            /*
                            binding.PriNome.setText("")
                            binding.ultNome.setText("")
                            binding.editEmail.setText("")
                            binding.etPassword.setText("")
                            binding.ConfPassword.setText("")

                             */
                            val userMap = hashMapOf("Nome" to name, "Ultimo_Nome" to lastName, "email" to email)
                            val user = FirebaseAuth.getInstance().currentUser
                            val uid = user?.uid
                            db.collection("Users").document(uid.toString())
                                .set(userMap).addOnCompleteListener{
                                    Log.d("db","Sucesso ao salvar dados do user!")
                                }.addOnFailureListener{

                                }
                            /*
                            val  snackbar1 = Snackbar.make(view,user.toString(),Snackbar.LENGTH_SHORT)
                            snackbar1.setBackgroundTint(Color.RED)
                            snackbar1.show()

                             */
                            navegarToHome()
                        }
                    }.addOnFailureListener{execption ->
                        val mensagemErr = when(execption){
                            is FirebaseAuthWeakPasswordException -> "Senha no minimo 6 caracteres!"
                            is FirebaseAuthInvalidCredentialsException -> "Email invalido!"
                            is FirebaseAuthUserCollisionException -> "Esta conta já existe!"
                            is FirebaseNetworkException -> "Sem conecção com a Internet!"
                            else -> "Euro ao fazer o Registro do Usuario!"
                        }
                        val  snackbar = Snackbar.make(view,mensagemErr,Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.show()
                    }

                }
            }
        }
    }
    private  fun navegarToHome(){
        val intent = Intent(this,Home::class.java)
        startActivity(intent)
        finish()
    }

}