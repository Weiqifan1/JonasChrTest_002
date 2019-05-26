package com.example.jonaschrtest_002

import android.Manifest
import android.os.Bundle

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.media.MediaPlayer
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.example.jonaschrtest_002.Database.DatabaseActivity
import com.sembozdemir.permissionskt.askPermissions
import com.sembozdemir.permissionskt.handlePermissionsResult

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_playsound.*

class MainActivity : Activity() {
    private var mediaPlayer: MediaPlayer? = null
    private val wantedPerm = Manifest.permission.WRITE_EXTERNAL_STORAGE
    private val TAG = MainActivity::class.java.name;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        actmain_playsoundBtn.setOnClickListener {
            startActivity(Intent(this, PlaySound::class.java))
        }
        actmain_todiskbooks.setOnClickListener {
            startActivity(Intent(this, DiskBooks::class.java))
        }
        actmain_recycle.setOnClickListener {
            startActivity(Intent(this, AudioFileList::class.java))
        }

        actmain_dbsqlite.setOnClickListener{
            //startActivity(Intent(this, Dbsqlite::class.java))
            startActivity(Intent(this, DatabaseActivity::class.java))
        }

//https://en.proft.me/2017/06/14/runtime-permissions-android-marshmallow-60-and-abo/
        askPermissions(wantedPerm) {
            onGranted {
                writeFile()
            }

            onDenied {
                Log.d(TAG, "WRITE_EXTERNAL_STORAGE permission is denied.")
            }

            onShowRationale { request ->
                Snackbar.make(
                    root_layout, "You should grant WRITE_EXTERNAL_STORAGE permission",
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction("Retry") { request.retry() }
                    .show()
            }

            onNeverAskAgain {
                Log.d(TAG, "Never ask again for WRITE_EXTERNAL_STORAGE permission")
            }

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        handlePermissionsResult(requestCode, permissions, grantResults)
    }

    private fun writeFile() {
        toast("Write file")
    }
}