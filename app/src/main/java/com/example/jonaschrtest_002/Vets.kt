package com.example.jonaschrtest_002

open class BookMark(val id: Int, var bookMarkName: String,var bookPath:String, var fromBook: String, var bookTime : Int = 0){

    companion object{
        val TABLE_NAME2 = "BookMarks"
        val ID = "id"
        val BOOKMARK_NAME = "name"
        val BOOK_Path = "bookPath"
        val FROM_BOOK = "fromBook"
        val BOOKTIME = "bookTime"
    }

}

val bookMarks = mutableListOf(
    BookMark(1, "All Time Low 1",
        "/storage/emulated/0/Music/All Time Low - Nothing Personal/01 Weigthless.mp3",
        "All Time Low",0 ),
    BookMark(2, "All Time Low 2",
        "/storage/emulated/0/Music/All Time Low - Nothing Personal/01 Weigthless.mp3",
        "All Time Low",0 )

    )

