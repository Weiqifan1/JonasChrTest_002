package com.example.jonaschrtest_002.Adapters

import com.example.jonaschrtest_002.Models.Audio

class AdioFileHelper {

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