package pt.ua.clima

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.String

class Home : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var temperatureSensor: Sensor? = null
    private var humiditySensor: Sensor? = null
    private var pressureSensor: Sensor? = null
    private var lightSensor: Sensor? = null
    companion object{
        const val wifiPermissionRequestCode = 1
    }

    private lateinit var wifiManager: WifiManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        val profileButton = findViewById<Button>(R.id.profileButtonH)
        profileButton.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val definitionsButton = findViewById<Button>(R.id.definitionsButtonH)
        definitionsButton.setOnClickListener{
            val intent = Intent(this, Definitions::class.java)
            startActivity(intent)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
    override fun onResume() {
        super.onResume()

        temperatureSensor?.let {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL

            )
        }
        humiditySensor?.let {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
        pressureSensor?.let {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
        lightSensor?.let {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_AMBIENT_TEMPERATURE -> {
                val temperature = event.values[0]
                val temperatureText = findViewById<TextView>(R.id.temperature)
                temperatureText.setText(String.valueOf(temperature))
            }
            Sensor.TYPE_RELATIVE_HUMIDITY -> {
                val humidity = event.values[0]
                val humidityText = findViewById<TextView>(R.id.humidity)
                humidityText.setText(String.valueOf(humidity))
            }
            Sensor.TYPE_PRESSURE -> {
                val pressure = event.values[0]
                val pressureText = findViewById<TextView>(R.id.pressure)
                pressureText.setText(String.valueOf(pressure))
            }
            Sensor.TYPE_LIGHT -> {
                val luminosity = event.values[0]
                val luminosityText = findViewById<TextView>(R.id.luminosity)
                luminosityText.setText(String.valueOf(luminosity))
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out kotlin.String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == wifiPermissionRequestCode){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
            else{

            }
        }
    }
}