package com.example.jonaschrtest_002

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import java.lang.IllegalArgumentException

class VetBaseOpenHelper(context: Context = App.instance) :
    ManagedSQLiteOpenHelper(context, "VetBase", null, 2) {
    companion object {
        val instance by lazy { VetBaseOpenHelper() }

        private var instancex: VetBaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): VetBaseOpenHelper {
            if (instancex == null) {
                instancex = VetBaseOpenHelper(context.getApplicationContext())
            }
            return instancex!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(BookMark.TABLE_NAME2, true,
            BookMark.ID to INTEGER + PRIMARY_KEY + UNIQUE,
            BookMark.BOOKMARK_NAME to TEXT,
            BookMark.BOOK_Path to TEXT,
            BookMark.FROM_BOOK to INTEGER,
            BookMark.BOOKTIME to INTEGER
        )

        db.insert(BookMark.TABLE_NAME2,
            BookMark.ID to 1,
            BookMark.BOOKMARK_NAME to "All Time Low Weightless bookmark 1",
            BookMark.BOOK_Path to "/storage/emulated/0/Music/All Time Low - Nothing Personal/01 Weigthless.mp3",
            BookMark.FROM_BOOK to "All Time Low Weightless",
            BookMark.BOOKTIME to 9
        )

        }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(BookMark.TABLE_NAME2, true)
        onCreate(db)
        }

    }


val Context.database: VetBaseOpenHelper
    get() = VetBaseOpenHelper.instance


val bookMarkParser = rowParser {

        id: Int,
        bookMarkName: String,
        bookPath: String,
        fromBook : String,
        bookTime: Int-> BookMark(id,
    bookMarkName,
    bookPath,
    fromBook,
    bookTime)
}

