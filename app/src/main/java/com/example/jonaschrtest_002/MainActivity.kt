package com.example.jonaschrtest_002

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import android.app.Activity
import android.media.MediaPlayer
import android.view.MotionEvent

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    private  var mediaPlayer:MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // setSupportActionBar(toolbar)



        mediaPlayer = MediaPlayer.create(this,R.raw.explosion)
        mediaPlayer?.setOnPreparedListener{
            (println("Ready to go"))

        /*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
                */
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
/*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    */
}
