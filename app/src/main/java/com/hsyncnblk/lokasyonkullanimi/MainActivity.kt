package com.hsyncnblk.lokasyonkullanimi

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.hsyncnblk.lokasyonkullanimi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var konumIzinKontrol=0
    private lateinit var flpc: FusedLocationProviderClient
    private lateinit var locationTask : Task<Location>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        flpc=LocationServices.getFusedLocationProviderClient(this)

        binding.buttonKonumAl.setOnClickListener {

            konumIzinKontrol= ContextCompat.checkSelfPermission(this@MainActivity,Manifest.permission.ACCESS_FINE_LOCATION)

            if (konumIzinKontrol != PackageManager.PERMISSION_GRANTED){ // izin onaylanmamıssa

                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),100)

            }else{// izin onaylanmıssa

                locationTask=flpc.lastLocation
                konumBilgisiAl()

            }


        }

    }

    fun konumBilgisiAl(){
    locationTask.addOnSuccessListener {
        if (it != null){

            binding.textViewEnlem.text="Enlem : ${it.latitude}"
            binding.textViewBoylam.text="Boylam : ${it.longitude}"

        }else{

            binding.textViewEnlem.text="Enlem : Alınamadı"
            binding.textViewBoylam.text="Boylam : Alınamadı"

        }
    }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode==100){

            konumIzinKontrol= ContextCompat.checkSelfPermission(this@MainActivity,Manifest.permission.ACCESS_FINE_LOCATION)


            if (grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                locationTask=flpc.lastLocation
                konumBilgisiAl()
                Toast.makeText(applicationContext,"izin kabul edildi",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"izin reddedildi",Toast.LENGTH_SHORT).show()
            }
        }

    }

}