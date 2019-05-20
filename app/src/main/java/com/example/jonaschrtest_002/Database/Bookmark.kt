package com.example.jonaschrtest_002.Database

class Bookmark(val id: Int, val timeInFIle: Long, val motherFilePath: String, val timeCreated: Long, val text: String) {

    companion object {
        val TABLE_NAME = "Bookmarks"
        val ID = "id"
        val MOTHER_FILE_PATH = "motherFilePath"
        val TIME_IN_FILE = "timeInFIle"
        val TIME_CREATED = "timeCreated"
        val TEXT = "text"

    }

}


val bookmarks = mutableListOf(
    Bookmark(1, 1000, "Ukendt strig", 123456789, "ingen fil 1 ssekund"),
    Bookmark(2, 2000, "Ukendt strig", 123456789, "ingen fil 2 sekunder"),
    Bookmark(3, 3000, "Ukendt strig", 123456789, "ingen fil 3 sekunder")

)