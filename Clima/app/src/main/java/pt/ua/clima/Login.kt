package pt.ua.clima

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import pt.ua.clima.databinding.ActivityLogin2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class Login : AppCompatActivity() {
    private lateinit var  binding:ActivityLogin2Binding
    private val auth = FirebaseAuth.getInstance()
    private lateinit var googleSignClient : GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val googleSignOpcion = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("783989715051-n4b4bpi46upmd6937p0pht2360uu78h4.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignClient = GoogleSignIn.getClient(this,googleSignOpcion)

        binding.btnSubmit.setOnClickListener{
            val email = binding.editEmail.text.toString()
            val senha = binding.etPassword.text.toString()

            if(email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha todos os campos!",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else{
                auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener{autenticacao ->
                    if (autenticacao.isSuccessful){
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
        binding.btnGogle.setOnClickListener{
                signWGoogle()
        }
        binding.gest.setOnClickListener{
            navegarToHome()
        }
    }

    private fun signWGoogle() {
       val intent = googleSignClient.signInIntent
        startActivity(intent)
        abreActivity.launch(intent)

    }
   private var abreActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result:ActivityResult ->

        if (result.resultCode == RESULT_OK){
           val intent =  result.data
           val task = GoogleSignIn.getSignedInAccountFromIntent(intent)

           try {
                val conta = task.getResult(ApiException::class.java)
                loginComGoogle(conta.idToken)
           } catch (exception: ApiException){

           }
        }
    }

    private fun loginComGoogle(idToken: String?) {
        val credencial = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credencial).addOnCompleteListener(this){
          task: Task<AuthResult> ->
            if (task.isSuccessful){
                Toast.makeText(baseContext,"Autenticação efetuada com google",
                Toast.LENGTH_SHORT).show()
                navegarToHome()
            }else{
                Toast.makeText(baseContext,"Erro de Autenticação com google",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private  fun navegarToHome(){
        val intent = Intent(this,Home::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        val userAtual = FirebaseAuth.getInstance().currentUser
        if (userAtual != null){
            navegarToHome()
        }
    }
}