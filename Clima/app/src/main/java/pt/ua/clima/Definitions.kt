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

        val celsiusButton = findViewById<Button>(R.id.celsius)
        celsiusButton.setOnClickListener{
            SettingsConstants.isCelsius = true
        }

        val farenheitButton = findViewById<Button>(R.id.farenheit)
        farenheitButton.setOnClickListener{
            SettingsConstants.isCelsius = false
        }

        val pascalButton = findViewById<Button>(R.id.pascal)
        pascalButton.setOnClickListener{
            SettingsConstants.isPascal = true
        }

        val barButton = findViewById<Button>(R.id.bar)
        barButton.setOnClickListener{
            SettingsConstants.isPascal = false
        }

        val shareButton = findViewById<Button>(R.id.shareSensors)
        shareButton.setOnClickListener{
            SettingsConstants.shareS = !SettingsConstants.shareS
        }

        val profileButton = findViewById<Button>(R.id.profileButtonD)
        profileButton.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val searchButton = findViewById<Button>(R.id.searchButtonD)
        searchButton.setOnClickListener{
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }
    }
}
