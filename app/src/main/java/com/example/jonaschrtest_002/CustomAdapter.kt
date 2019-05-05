package com.example.jonaschrtest_002

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jonaschrtest_002.Models.AudioModels
import com.example.jonaschrtest_002.Models.User

class CustomAdapter(val userList: ArrayList<AudioModels>):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val user: AudioModels = userList[p1]
        p0.textViewName.text = user.aName
        p0.textViewAddress.text = user.aAlbum
        p0.textViewAddress2.text = user.aArtist
        p0.textViewAddress3.text = user.aPath
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int):
            ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.list_layout, p0, false)
        return ViewHolder(v)
    }




    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewName = itemView.findViewById(R.id.textViewName) as TextView
        val textViewAddress = itemView.findViewById(R.id.textViewAddress) as TextView
        val textViewAddress2 = itemView.findViewById(R.id.textViewAddress2) as TextView
        val textViewAddress3 = itemView.findViewById(R.id.textViewAddress3) as TextView
    }


}