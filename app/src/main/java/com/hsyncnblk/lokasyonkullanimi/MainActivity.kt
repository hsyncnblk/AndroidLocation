package com.hsyncnblk.lokasyonkullanimi

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hsyncnblk.lokasyonkullanimi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var konumIzinKontrol=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonKonumAl.setOnClickListener {

            konumIzinKontrol= ContextCompat.checkSelfPermission(this@MainActivity,Manifest.permission.ACCESS_FINE_LOCATION)

            if (konumIzinKontrol != PackageManager.PERMISSION_GRANTED){ // izin onaylanmamıssa

                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),100)

            }else{// izin onaylanmıssa



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
            if (grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(applicationContext,"izin kabul edildi",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"izin reddedildi",Toast.LENGTH_SHORT).show()
            }
        }

    }

}