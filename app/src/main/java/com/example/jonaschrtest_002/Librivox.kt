package com.example.jonaschrtest_002

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_librivox.*

class Librivox: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_librivox)

        downloadHardcoded.setOnClickListener{
            Toast.makeText(this, "Du klikkede!", Toast.LENGTH_SHORT).show()
        }
    }

    //val downloadButton =



}
