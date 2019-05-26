package com.example.jonaschrtest_002.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class ForecastDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx,
    ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {

    companion object{
        const val DB_NAME = "forecast.db"
        const val DB_VERSION = 1
        val instance by lazy {ForecastDbHelper()}
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(
            CityForecastTable.NAME, true,
            CityForecastTable.ID to INTEGER + PRIMARY_KEY,
            CityForecastTable.CITY to TEXT,
            CityForecastTable.COUNTRY to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CityForecastTable.NAME, true)
        onCreate(db)
    }



}