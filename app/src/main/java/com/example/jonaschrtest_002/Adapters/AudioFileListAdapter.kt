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
import com.example.jonaschrtest_002.Adapters.AudioFileListAdapter.Utils.startNewActivity
import com.example.jonaschrtest_002.AudioFileList
import com.example.jonaschrtest_002.MainActivity
import com.example.jonaschrtest_002.Models.Audio
import com.example.jonaschrtest_002.PlaySound
import com.example.jonaschrtest_002.R
import kotlinx.android.synthetic.main.activity_audiofilelist.*
import kotlinx.android.synthetic.main.activity_main.*


class AudioFileListAdapter(val userList: ArrayList<Audio>, private val context: Context):
    RecyclerView.Adapter<AudioFileListAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val mContext = p0.itemView.context
        val user: Audio = userList[p1]
        p0.textViewName.text = user.aShortPath
        p0.textViewAddress.text = user.aAlbum
        p0.textViewAddress2.text = user.aArtist
        p0.textViewAddress3.text = user.aName
        val audio_click_me = p0.textViewName

        audio_click_me.setOnClickListener {
            if (context is AudioFileList) {
                (context as AudioFileList).chosenAudioFile = user
                (context as AudioFileList).setCurrentFileChosen(user.aPath)
                if((context as AudioFileList).pathIsAFile((context as AudioFileList).chosenAudioFile) && (context as AudioFileList).chosenAudioFile.aAudList == ArrayList<Audio>() ){
                    startNewActivity((context as AudioFileList), PlaySound::class.java,(context as AudioFileList).chosenAudioFile)
                }else {
                    val gatherFolder2 = (context as AudioFileList).gatherInTopSubFolders((context as AudioFileList).chosenAudioFile.aAudList)
                    (context as AudioFileList).chosenAudioListOfList.add(gatherFolder2)
                    (context as AudioFileList).audiofilelist_folderDepth.text = (context as AudioFileList).chosenAudioListOfList.size.toString()
                    val adapter2 = AudioFileListAdapter(gatherFolder2, (context as AudioFileList))
                    (context as AudioFileList).recyclerView.adapter = adapter2
                }
            }
        }
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
            intent.putExtra("valor", user.aPath)
            intent.putExtra("valorName" ,user.aName)
            intent.putExtra("valorBookMarkTime", user.bookMarkTime)
            context.startActivity(intent)
        }
    }
}