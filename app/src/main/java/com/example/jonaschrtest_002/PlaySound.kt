package com.example.jonaschrtest_002

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_playsound.*
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.widget.SeekBar
import android.widget.Toast
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class PlaySound : AppCompatActivity() {


    private lateinit var player: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    var bookMarkContainer = database.use {
        select("BookMarks").parseList(bookMarkParser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playsound)
        var startPlayer = true


        val intent = getIntent();
        var myValue = intent.getStringExtra("valor")
        val myValueName = intent.getStringExtra("valorName")
        val myValorBookMarkTime = intent.getStringExtra("valorBookMarkTime")

        playSound_addBookmark.setOnClickListener {
            val pathToOurAudioFile = myValue
            val currentTime = player.currentPosition
            val idToUse = getLargestBookMarkId(bookMarkContainer) +1

            val EtNytBogmaerke = BookMark(
                idToUse,
                myValueName + " " + idToUse.toString(),
                pathToOurAudioFile,
                "BookTime: " + (currentTime / 1000).toString() + "Seconds",
                currentTime
            )


            database.use {
                insert(
                    BookMark.TABLE_NAME2,
                    BookMark.ID to EtNytBogmaerke.id,
                    BookMark.BOOKMARK_NAME to EtNytBogmaerke.bookMarkName,
                    BookMark.BOOK_Path to EtNytBogmaerke.bookPath,
                    BookMark.FROM_BOOK to EtNytBogmaerke.fromBook,
                    BookMark.BOOKTIME to EtNytBogmaerke.bookTime
                )
            }

        }

        // Start the media player
        button_play.setOnClickListener {
            if (startPlayer == true) {


                //If no book have been chosen it will play default sound bit
                if (myValue == null) {
                    player = MediaPlayer.create(this, R.raw.explosion)
                    player.start()
                    song_title.text = myValueName
                    tv_duration.text = "${player.seconds} sec"
                    initializeSeekBar()
                    startPlayer = false

                    it.isEnabled = false
                    button_stop.isEnabled = true
                    button_fast_forward.isEnabled = true
                    button_fast_backward.isEnabled = true
                    button_play.isEnabled = true
                    button_mid_forward.isEnabled = true
                    button_mid_backward.isEnabled = true
                    button_slow_forward.isEnabled = true
                    button_slow_backward.isEnabled = true

                } else {


                    val data = Uri.parse(myValue)
                    player = MediaPlayer()

                    player.apply {
                        setDataSource(applicationContext, data)
                        try {
                            prepare()
                        } catch (e: IllegalStateException) {
                            null
                        }

                        val currentSec = player.currentSeconds + myValorBookMarkTime.toInt()
                        player.seekTo(currentSec)

                        start()
                    }
                    song_title.text = myValueName
                    tv_duration.text = "${player.seconds} sec"
                    initializeSeekBar()
                    startPlayer = false

                    it.isEnabled = false
                    button_stop.isEnabled = true
                    button_fast_forward.isEnabled = true
                    button_fast_backward.isEnabled = true
                    button_play.isEnabled = true
                    button_mid_forward.isEnabled = true
                    button_mid_backward.isEnabled = true
                    button_slow_forward.isEnabled = true
                    button_slow_backward.isEnabled = true
                }
            } else {
                player.start()
                button_stop.isEnabled = true
                button_play.isEnabled = true
            }
        }
        // Stop the media player
        button_stop.setOnClickListener {
            if (player.isPlaying) {
                player.pause()

                it.isEnabled = false
                button_play.isEnabled = true
            }
        }

        // +5 sec
        button_fast_forward.setOnClickListener {
            val currentSec = player.currentSeconds + 5
            player.seekTo(currentSec * 1000)
        }

        // +30 sec
        button_mid_forward.setOnClickListener {
            val currentSec = player.currentSeconds + 30
            player.seekTo(currentSec * 1000)
        }

        // +10 min
        button_slow_forward.setOnClickListener {
            val currentSec = player.currentSeconds + 600
            player.seekTo(currentSec * 1000)
        }

        // -5 sec
        button_fast_backward.setOnClickListener {
            val currentSec = player.currentSeconds - 5
            if (player.currentSeconds <= 5) {
                player.seekTo(0)
            } else {
                player.seekTo(currentSec * 1000)
            }
        }

        // -30 sec
        button_mid_backward.setOnClickListener {
            val currentSec = player.currentSeconds - 30
            if (player.currentSeconds <= 30) {
                player.seekTo(0)
            } else {
                player.seekTo(currentSec * 1000)
            }
        }

        // -10 min
        button_slow_backward.setOnClickListener {
            val currentSec = player.currentSeconds - 600
            if (player.currentSeconds <= 600) {
                player.seekTo(0)
            } else {
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

    fun getLargestBookMarkId(bookMarkContainer: List<BookMark>): Int{
        var largestId = 0
        for (item in bookMarkContainer){
            if (item.id >= largestId){
                largestId = item.id
            }
        }
        return largestId
    }

    override fun onBackPressed()  {
        if (player.isPlaying()) {
            player.stop();
        }else{

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



