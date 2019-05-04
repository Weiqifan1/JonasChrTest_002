package com.example.jonaschrtest_002

import android.media.MediaPlayer
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_playsound.*
import android.view.MotionEvent

class PlaySound : AppCompatActivity(){

    private  var mediaPlayer:MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playsound)


        mediaPlayer = MediaPlayer.create(this,R.raw.explosion)
        mediaPlayer?.setOnPreparedListener{
            (println("Ready to go"))


        }

        pushButton.setOnTouchListener{ _,event ->
            handleTouch(event)
            true

        }

    }


    private fun handleTouch(event: MotionEvent){
        when (event.action){
            MotionEvent.ACTION_DOWN ->{
                println("down")
                mediaPlayer?.start()
            }
            MotionEvent.ACTION_CANCEL,MotionEvent.ACTION_UP -> {
                println("up or cancel")
                mediaPlayer?.pause()
                mediaPlayer?.seekTo(0)
            }
            else -> {
                println("other")
            }
        }
    }




}



/*

class MainActivity : Activity() {
    private  var mediaPlayer:MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // setSupportActionBar(toolbar)


        mediaPlayer = MediaPlayer.create(this,R.raw.explosion)
        mediaPlayer?.setOnPreparedListener{
            (println("Ready to go"))


        }

        pushButton.setOnTouchListener{ _,event ->
            handleTouch(event)
            true

        }


    }
    private fun handleTouch(event: MotionEvent){
        when (event.action){
            MotionEvent.ACTION_DOWN ->{
                println("down")
                mediaPlayer?.start()
            }
            MotionEvent.ACTION_CANCEL,MotionEvent.ACTION_UP -> {
                println("up or cancel")
                mediaPlayer?.pause()
                mediaPlayer?.seekTo(0)
            }
            else -> {
                println("other")
            }
        }
    }

}

*/
