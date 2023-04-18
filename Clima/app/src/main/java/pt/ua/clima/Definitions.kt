package pt.ua.clima

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView

class Definitions: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definitions)

        val profileButton = findViewById<Button>(R.id.profileButtonD)
        profileButton.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val definitionsButton = findViewById<Button>(R.id.definitionsButtonD)
        definitionsButton.setOnClickListener{
            val intent = Intent(this, Definitions::class.java)
            startActivity(intent)
        }
    }
}
