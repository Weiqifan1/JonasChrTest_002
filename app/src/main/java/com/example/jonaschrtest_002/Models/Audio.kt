package com.example.jonaschrtest_002.Models

data class Audio(
    val aPath: String,
    val aName: String?,
    val aAlbum:String?,
    val aArtist: String?,
    val aFolderOrFile: String,
    var aAudList: ArrayList<Audio>?
)
