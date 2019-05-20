package com.example.jonaschrtest_002

import android.app.Activity
import android.os.Bundle
import com.example.jonaschrtest_002.Database.Bookmark
import com.example.jonaschrtest_002.Database.bookmarks
import kotlinx.android.synthetic.main.activity_dbhelp.*

class Dbhelp: Activity()  {

    val bookmarkList: List<Bookmark> = bookmarks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbhelp)



        var displayBookmarks = ""
        for (item in bookmarkList) {
            displayBookmarks += "\n\n" + item.toString()
        }

        dbhelp_text.text = displayBookmarks

    }
}
