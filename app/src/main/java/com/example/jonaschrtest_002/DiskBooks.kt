package com.example.jonaschrtest_002

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_diskbooks.*
import android.content.Context
import android.widget.Toast
import com.example.jonaschrtest_002.Models.Audio





class DiskBooks: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diskbooks)

        actdiskbooks_name.text = "DiskBooks.kt"

        //actdiskbooks_mp3strings.text = "her skal der vaere tekst om de mp3 strings jeg kan finde"
        actdiskbooks_mp3strings.text = getAllAudioFromDevice(this).toString();






        }



    //2019-05-04 kl. 19.56
    //https://riptutorial.com/android/example/23916/fetch-audio-mp3-files-from-specific-folder-of-device-or-fetch-all-files
    fun getAllAudioFromDevice(context: Context): ArrayList<Audio> {
        lateinit var result: ArrayList<Audio>
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
                    c.getString(3),
                    "File",
                    ArrayList<Audio>(),
                    c.getString(0),
                    "0"
                )

                /*

                data class Audio(
                    val aPath: String,
                    val aName: String?,
                    val aAlbum:String?,
                    val aArtist: String?,
                    val aFolderOrFile: String,
                    var aAudList: ArrayList<Audio>?
                )
                 */

                tempAudioList.add(audioModel)
            }
            c.close()
        }

        if (tempAudioList.size > 0){
            result = removeStoragePath(tempAudioList)
            return result
        } else {
            return ArrayList<Audio>()
        }

    }


    fun removeStoragePath(listOfAudioFiles: ArrayList<Audio>):
            ArrayList<Audio>{
        val result = ArrayList<Audio>()
        val storage: String = "/storage/emulated/0"
        for (item in listOfAudioFiles) {
            val topdirectory = item.aPath.subSequence(storage.length, item.aPath.length)
            val newAudio = Audio(topdirectory.toString(), item.aName, item.aAlbum, item.aArtist, item.aFolderOrFile, ArrayList<Audio>(), item.aPath, "0")
            result.add(newAudio)
        }
        return result
    }




}




/*

    actdiskbooks_mp3strings.text = scanDeviceForMp3Files().size.toString()

    //Stackoverflow.com - Lazy Ninja - edited Jan 30 '17 at 7:20  answered Jun 3 '14 at 2:14
    //https://stackoverflow.com/questions/21496626/get-mp3-format-files-from-android-device
    //(konverteret fra java til kotlin)
    private fun scanDeviceForMp3Files(): List<String> {
        //val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        val projection = arrayOf(
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION
        )
        val sortOrder = MediaStore.Audio.AudioColumns.TITLE + " COLLATE LOCALIZED ASC"
        val mp3Files = ArrayList<String>()

        var cursor: Cursor? = null
        try {
            val uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            cursor = contentResolver.query(uri, projection, selection, null, sortOrder)
            if (cursor != null) {
                cursor!!.moveToFirst()

                while (!cursor!!.isAfterLast()) {
                    val title = cursor!!.getString(0)
                    val artist = cursor!!.getString(1)
                    val path = cursor!!.getString(2)
                    val displayName = cursor!!.getString(3)
                    val songDuration = cursor!!.getString(4)
                    cursor!!.moveToNext()
                    if (path != null && path!!.endsWith(".mp3")) {
                        mp3Files.add(path)
                    }
                }

            }

            // print to see list of mp3 files
            for (file in mp3Files) {
                Log.i("TAG", file)
            }

        } catch (e: Exception) {
            Log.e("TAG", e.toString())
        } finally {
            if (cursor != null) {
                cursor!!.close()
            }
        }
        return mp3Files
    }

 */
