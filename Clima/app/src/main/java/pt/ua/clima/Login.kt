package pt.ua.clima

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

                    }
                }
            }
        }
 /*
        var et_user_name = findViewById(R.id.et_user_name) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_reset = findViewById(R.id.btn_regist) as Button
        var btn_submit = findViewById(R.id.btn_submit) as Button

        btn_reset.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            et_user_name.setText("")
            et_password.setText("")
        }

        // set on-click listener
        btn_submit.setOnClickListener {
            val user_name = et_user_name.text;
            val password = et_password.text;
            Toast.makeText(this, user_name, Toast.LENGTH_LONG).show()

            // your code to validate the user_name and password combination
            // and verify the same

        }
  */
    }
    private  fun
}