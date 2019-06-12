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
import com.example.jonaschrtest_002.Adapters.AudioFileListAdapter.Utils.startNewActivity
import com.example.jonaschrtest_002.Models.Audio
import kotlinx.android.synthetic.main.activity_audiofilelist.*
import org.jetbrains.anko.longToast

class AudioFileList : AppCompatActivity() {

    var chosenAudioFile: Audio = Audio("none Chosen", "none", "", "", "", ArrayList<Audio>(), "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_audiofilelist)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val audioInfoList : ArrayList<Audio> = getAllAudioFromDevice(this)
        //*********2019-06-11***************************************************************************
        val gatherFolder = gatherInTopSubFolders(audioInfoList)
        //******************************************************************************************
        val adapter = AudioFileListAdapter(gatherFolder, this)
        recyclerView.adapter = adapter

        /*
        audiofilelist_playBtn.setOnClickListener{

            //startNewActivity(this, PlaySound::class.java,chosenAudioFile)

            if (pathIsAFile(chosenAudioFile) && chosenAudioFile.aAudList == ArrayList<Audio>()){
                startNewActivity(this, PlaySound::class.java,chosenAudioFile)
            }else {
                val gatherFolder2 = gatherInTopSubFolders(chosenAudioFile.aAudList)
                val adapter2 = AudioFileListAdapter(gatherFolder2, this)
                recyclerView.adapter = adapter2
            }


        }
        */
    }

    fun setCurrentFileChosen(nameOfAudioFile: String?){
        audiofilelist_currentAudioFile.text = "Current Audio file: " + nameOfAudioFile
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
                    c.getString(0)
                )


                //Log.e("Name :$aPath", " Album :$album")
                //Log.e("Path :$path", " Artist :$artist")

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

    //2019-06-11
    // helper files:


    fun removeStoragePath(listOfAudioFiles: ArrayList<Audio>):
            ArrayList<Audio>{
        val result = ArrayList<Audio>()
        val storage: String = "/storage/emulated/0"
        for (item in listOfAudioFiles) {
            val topdirectory = item.aPath.subSequence(storage.length, item.aPath.length)
            val newAudio = Audio(item.aPath, item.aName, item.aAlbum, item.aArtist, item.aFolderOrFile, ArrayList<Audio>(), topdirectory.toString())
            result.add(newAudio)
        }
        return result
    }


    fun gatherInTopSubFolders(flatAudioList: ArrayList<Audio>):ArrayList<Audio>{
        val newAudioList = ArrayList<Audio>()
        val listOfTopFolders = ArrayList<String>()
        for (item in flatAudioList){
            if (pathIsAFile(item)) {
                newAudioList.add(item)
            }else {
                val topFolder = getTopFolder(item)
                if (topFolder !in listOfTopFolders){
                    val allAudiosOfThatFolder = getAllAudioWithTopFolder(topFolder, flatAudioList)
                    //******************************
                    //remove top folder

                    val allAudiosOfThatFolder_withShorterShortPath = ArrayList<Audio>()
                    for (foundAudio in allAudiosOfThatFolder) {
                        val isItAFile = pathIsAFile(removeTopFolder(foundAudio))
                        lateinit var AudioWithShorterShortpath: Audio
                        if (isItAFile) {
                            AudioWithShorterShortpath = Audio(foundAudio.aPath, foundAudio.aName, foundAudio.aAlbum, foundAudio.aArtist, "File", foundAudio.aAudList, removeTopFolder(foundAudio).aShortPath)
                        }else {
                            AudioWithShorterShortpath = Audio(foundAudio.aPath, foundAudio.aName, foundAudio.aAlbum, foundAudio.aArtist, "Folder", foundAudio.aAudList, removeTopFolder(foundAudio).aShortPath)
                        }
                        allAudiosOfThatFolder_withShorterShortPath.add(AudioWithShorterShortpath)

                    }
                    //******************************
                    val folderObject = Audio(item.aPath, "topfolder: "+topFolder, "size: "+allAudiosOfThatFolder.size, "", "Folder", allAudiosOfThatFolder_withShorterShortPath, removeTopFolder(item).aShortPath)
                    newAudioList.add(folderObject)
                    listOfTopFolders.add(topFolder)
                }
            }
        }
        return newAudioList
    }


    fun getAllAudioWithTopFolder(topFolder: String, audioList: ArrayList<Audio>): ArrayList<Audio>{
        val result = ArrayList<Audio>()
        for (item in audioList) {
            if (!pathIsAFile(item)) {
                val folder = getTopFolder(item)
                if (topFolder == folder) {
                    result.add(item)
                }
            }
        }
        return result
    }


    fun getAllFolders(audioList: ArrayList<Audio>): ArrayList<String>{
        val result = ArrayList<String>()
        for (item in audioList) {
            if (pathIsAFile(item)) {
                val topFolder = getTopFolder(item)
                result.add(topFolder)
            }
        }
        result.toSet()
        val secondResult = ArrayList<String>()
        for (item in result) {
            secondResult.add(item)
        }
        return secondResult
    }


    fun getTopFolder(audioObject: Audio): String{
        val shortpath = audioObject.aShortPath
        val regex = """(/[^/]+)(/.*)""".toRegex()
        val matchResult = regex.find(shortpath)
        val (part1, part2) = matchResult!!.destructured
        return part1//.subSequence(1, part1.length).toString()
    }


    fun removeTopFolder(audioObject: Audio): Audio{
        //find our if 2 files have the same top directory:
        val shortpath = audioObject.aShortPath
        val regex = """(/[^/]+)(/.*)""".toRegex()
        val matchResult = regex.find(shortpath)
        val (part1, part2) = matchResult!!.destructured
        //println(part1)
        //println(part2)
        val newaudioobject = Audio(audioObject.aPath, audioObject.aName,audioObject.aAlbum,audioObject.aArtist,audioObject.aFolderOrFile,audioObject.aAudList,part2)
        return newaudioobject
    }

    fun pathIsAFile(audioObject: Audio): Boolean{
        val shortpath: String = audioObject.aShortPath
        if ("/" !in shortpath.subSequence(1,shortpath.length)){
            return true
        }else{
            return false
        }
    }

}


