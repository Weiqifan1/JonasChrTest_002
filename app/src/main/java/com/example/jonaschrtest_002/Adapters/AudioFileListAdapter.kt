package com.example.jonaschrtest_002.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.jonaschrtest_002.Models.Audio
import com.example.jonaschrtest_002.R

class AudioFileListAdapter(val userList: ArrayList<Audio>, private val context: Context):
    RecyclerView.Adapter<AudioFileListAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val user: Audio = userList[p1]
        p0.textViewName.text = user.aName
        p0.textViewAddress.text = user.aAlbum
        p0.textViewAddress2.text = user.aArtist
        p0.textViewAddress3.text = user.aPath

        // test: https://www.youtube.com/watch?v=k6GSQRnDGog
        val audio_click_me = p0.textViewName
        audio_click_me.setOnClickListener {
            // your code to run when the user clicks on the TextView
            Toast.makeText(context, user.aPath, Toast.LENGTH_SHORT).show()
        }

    }



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int):
            ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.audiofilelistadapter, p0, false)
        return ViewHolder(v)
    }




    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewName = itemView.findViewById(R.id.textViewName) as TextView
        val textViewAddress = itemView.findViewById(R.id.textViewAddress) as TextView
        val textViewAddress2 = itemView.findViewById(R.id.textViewAddress2) as TextView
        val textViewAddress3 = itemView.findViewById(R.id.textViewAddress3) as TextView
    }


}