package com.example.jonaschrtest_002.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.getExternalCacheDirs
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.jonaschrtest_002.AudioFileList
import com.example.jonaschrtest_002.MainActivity
import com.example.jonaschrtest_002.Models.Audio
import com.example.jonaschrtest_002.PlaySound
import com.example.jonaschrtest_002.R
import kotlinx.android.synthetic.main.activity_main.*
import com.example.jonaschrtest_002.Adapters.AdioFileHelper


class AudioFileListAdapter(val userList: ArrayList<Audio>, private val context: Context):
    RecyclerView.Adapter<AudioFileListAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        val listOfAudioList = getListOfSameFolders(userList)
        return listOfAudioList.size
    }



    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        //***********************************
        //2019-06-10
        val listOfAudioList = getListOfSameFolders(userList)
        //***********************************

        val eachFolder: ArrayList<Audio> = listOfAudioList[p1]
        if (eachFolder.size > 0){
            p0.textViewName.text = eachFolder[0].aAlbum
            p0.textViewAddress.text = eachFolder[0].aName
            p0.textViewAddress2.text = eachFolder[0].aArtist
            p0.textViewAddress3.text = eachFolder[0].aPath
        } else {
            p0.textViewName.text = "blank"
        }

        val audio_click_me = p0.textViewName
        

        /*
        val mContext = p0.itemView.context
        val user: Audio = userList[p1]

        p0.textViewName.text = user.aName
        p0.textViewAddress.text = user.aAlbum
        p0.textViewAddress2.text = user.aArtist
        p0.textViewAddress3.text = user.aPath

        // test: https://www.youtube.com/watch?v=k6GSQRnDGog
        val audio_click_me = p0.textViewName
        val audio_click_me2 = p0.textViewName

        audio_click_me.setOnClickListener {
            //Utils.startNewActivity(context, PlaySound::class.java,user)
            //Toast.makeText(context, user.aName, Toast.LENGTH_SHORT).show()
            if (context is AudioFileList) {
                //Toast.makeText(context, user.aName, Toast.LENGTH_SHORT).show()
                (context as AudioFileList).chosenAudioFile = user
                (context as AudioFileList).setCurrentFileChosen(user.aName)
            }
        }
        */
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int):
            ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.audiofilelistadapter, p0, false)
        return ViewHolder(v)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.textViewName) as TextView
        val textViewAddress = itemView.findViewById(R.id.textViewAddress) as TextView
        val textViewAddress2 = itemView.findViewById(R.id.textViewAddress2) as TextView
        val textViewAddress3 = itemView.findViewById(R.id.textViewAddress3) as TextView

    }
//https://stackoverflow.com/questions/49745488/how-to-start-a-new-activity-from-a-non-activity-class-in-android-kotlin
    object Utils {

        fun startNewActivity(context: Context, clazz: Class<*>, user:Audio) {

            val intent = Intent(context, clazz)
// To pass any data to next activity
            intent.putExtra("valor", user.aPath)
            intent.putExtra("valorName" ,user.aName)
// start your next activity
            context.startActivity(intent)

        }


    }


    fun getClosestFolderFromAudio(audioFile: Audio): String{
        val filePath = audioFile.aPath
        val reverse = filePath.reversed()
        val regex = """([^/]+)/(.*)""".toRegex()
        val matchResult = regex.find(reverse)
        val (part1, part2) = matchResult!!.destructured
        //part1.reversed() == MYFILe.mp3
        //part2.reversed() == /storage/.../Doyle eg./storage/emulated/0/Books/Audiobooks/Doyle
        return part2.reversed()
    }

    fun getAllAudioWithPath(audioList: ArrayList<Audio>, closestFolder: String)
            :ArrayList<Audio>{
        val resultList = ArrayList<Audio>()
        for (item in audioList){
            val closestFolderCheck = getClosestFolderFromAudio(item)
            if (closestFolder == closestFolderCheck){
                resultList.add(item)
            }
        }
        return resultList
    }


    fun getListOfSameFolders(audioFileList: ArrayList<Audio>):
            ArrayList<ArrayList<Audio>>{
        val folders = ArrayList<String>()
        val folderList = ArrayList<ArrayList<Audio>>()

        for (item in audioFileList){
            val closestFolder = getClosestFolderFromAudio(item)
            if (closestFolder !in folders){
                folders.add(closestFolder)
                folderList.add(getAllAudioWithPath(audioFileList, closestFolder))
            }
        }
        return folderList
    }

}