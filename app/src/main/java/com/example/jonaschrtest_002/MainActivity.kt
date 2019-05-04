package com.example.jonaschrtest_002

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.view.MotionEvent

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    private  var mediaPlayer:MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actmain_playsoundBtn.setOnClickListener{
            startActivity(Intent(this, PlaySound::class.java))
        }



    }


}
