package com.example.jonaschrtest_002

import android.content.Context
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.jonaschrtest_002.Adapters.AudioFileListAdapter
import com.example.jonaschrtest_002.Models.Audio

class AudioFileList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_audiofilelist)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val audioInfoList : ArrayList<Audio> = getAllAudioFromDevice(this)
        val adapter = AudioFileListAdapter(audioInfoList, this)
        recyclerView.adapter = adapter





    }



    //2019-05-04 kl. 19.56
    //https://riptutorial.com/android/example/23916/fetch-audio-mp3-files-from-specific-folder-of-device-or-fetch-all-files
    fun getAllAudioFromDevice(context: Context): ArrayList<Audio> {
        val tempAudioList = ArrayList<Audio>()

        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.AudioColumns.DATA,
            MediaStore.Audio.AudioColumns.TITLE,
            MediaStore.Audio.AudioColumns.ALBUM,
            MediaStore.Audio.ArtistColumns.ARTIST
        )
        //val c = context.getContentResolver()
        //    .query(uri, projection, MediaStore.Audio.Media.DATA + " like ? ", arrayOf("%utm%"), null)
        val c = context.getContentResolver().query(
            uri,
            projection,
            null, null, null
        )

        if (c != null) {
            while (c.moveToNext()) {
                val audioModel = Audio(
                    c.getString(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3)
                )


                //Log.e("Name :$aPath", " Album :$album")
                //Log.e("Path :$path", " Artist :$artist")

                tempAudioList.add(audioModel)
            }
            c.close()
        }


        return tempAudioList

    }



}


