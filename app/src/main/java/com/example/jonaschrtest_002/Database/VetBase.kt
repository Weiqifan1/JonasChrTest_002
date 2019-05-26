package com.example.jonaschrtest_002.Database


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
        // Here you create tables
        db.createTable(Pet.TABLE_NAME, true,
            Pet.DTYPE to TEXT,
            Pet.ID to INTEGER + PRIMARY_KEY + UNIQUE,
            Pet.NAME to TEXT,
            Pet.ALIVE to INTEGER
        )

        db.insert(Pet.TABLE_NAME,
            Pet.DTYPE to "Cat",
            Pet.ID to 1,
            Pet.NAME to "Felix",
            Pet.ALIVE to 1
        )
        db.insert("Pets",
            Pet.DTYPE to "Dog",
            "id" to 2,
            "name" to "Rufus",
            "alive" to 1
        )
        db.insert("Pets",
            Pet.DTYPE to "Pet",
            "id" to 3,
            "name" to "Killer",
            "alive" to 1
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Pet.TABLE_NAME, true)
        onCreate(db)
    }


    val Context.database: VetBaseOpenHelper
        get() = VetBaseOpenHelper.instance

    val petParser = rowParser {
            dtype: String,
            id: Int,
            name: String,
            alive: Int,
            livesLeft: Int,
            barkPitch: String ->
        when (dtype) {
            "Pet" -> Pet(id, name, alive != 0)
            else -> throw IllegalArgumentException("no such type $dtype")
        }
    }




}
