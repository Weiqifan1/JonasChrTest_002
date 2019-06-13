package com.example.jonaschrtest_002

import android.media.MediaPlayer
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_playsound.*
import android.view.MotionEvent
import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.AudioManager
import android.net.Uri
import android.os.Environment
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import org.jetbrains.anko.longToast

class PlaySound : AppCompatActivity(){

    private  var mediaPlayer:MediaPlayer? = null

    private lateinit var player: MediaPlayer
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playsound)
        var startPlayer = true

       // val user = intent.getStringExtra("valor")
        val intent = getIntent();
        var myValue = intent.getStringExtra("valor")
        val myValueName = intent.getStringExtra("valorName")


        actplaysound_startTime.setOnClickListener{
            var valueFromTime = actplaysound_displayTime.text
            longToast(valueFromTime).show()
        }

        actplaysound_editTime.setOnClickListener{
            longToast("du klikkede edit time").show()

        }

        // Start the media player
        button_play.setOnClickListener {
            if (startPlayer == true){

                val data = Uri.parse (myValue)

                 player = MediaPlayer().apply {
                    setDataSource(applicationContext, data)
                    try { prepare() } catch (e: IllegalStateException) { null}
                    start()
                }
                song_title.text = myValueName


                tv_duration.text = "${player.seconds} sec"
                initializeSeekBar()
                startPlayer=false

                it.isEnabled = false
                button_stop.isEnabled = true
                button_fast_forward.isEnabled = true
                button_fast_backward.isEnabled = true
                button_play.isEnabled = true
            }else{
                player.start()
                button_stop.isEnabled = true
                button_play.isEnabled = true
            }
        }
        // Stop the media player
        button_stop.setOnClickListener{
            if(player.isPlaying){
                player.pause()

                it.isEnabled = false
                button_play.isEnabled = true
            }
        }




        // +5 sec
        button_fast_forward.setOnClickListener{
            val currentSec = player.currentSeconds +5

            player.seekTo(currentSec * 1000)

        }
        // -5 sec
        button_fast_backward.setOnClickListener {


            val currentSec = player.currentSeconds - 5

            if (player.currentSeconds <=5){
                player.seekTo(0 * 1000)
            }else {
                player.seekTo(currentSec * 1000)
            }



        }


        // Seek bar change listener
        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    player.seekTo(i * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    override fun onBackPressed()  {
        if (player.isPlaying()) {
            player.stop();
        }
        super.onBackPressed();
       
    }


    // Method to initialize seek bar and audio stats
    private fun initializeSeekBar() {
        seek_bar.max = player.seconds

        runnable = Runnable {
            seek_bar.progress = player.currentSeconds

            tv_pass.text = "${player.currentSeconds} sec"
            val diff = player.seconds - player.currentSeconds
            tv_due.text = "$diff sec"

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }
}



// Extension property to get media player duration in seconds
val MediaPlayer.seconds:Int
    get() {
        return this.duration / 1000
    }


// Extension property to get media player current position in seconds
val MediaPlayer.currentSeconds:Int
    get() {
        return this.currentPosition/1000
    }


// Extension function to show toast message quickly
fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}



/*

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

*/
