package com.example.jonaschrtest_002

import android.os.Bundle

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    private  var mediaPlayer:MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actmain_playsoundBtn.setOnClickListener{
            startActivity(Intent(this, PlaySound::class.java))
        }

        actmain_todiskbooks.setOnClickListener{
            startActivity(Intent(this, DiskBooks::class.java))
        }

        actmain_recycle.setOnClickListener{
            startActivity(Intent(this, India::class.java))
        }

    }


}
