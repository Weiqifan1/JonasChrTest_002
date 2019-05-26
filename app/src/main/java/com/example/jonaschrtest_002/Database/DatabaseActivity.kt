package com.example.jonaschrtest_002.Database


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.jonaschrtest_002.R
import kotlinx.android.synthetic.main.activity_dbsqlite.*
//import kotlinx.android.synthetic.main.activity_database.*
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

/*

koden ligger  i Dbsqlite
denne klasse bliver ikke brugt

 */



class DatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_database)
        setContentView(R.layout.activity_dbsqlite)
    }

    override fun onResume() {
        super.onResume()
        val dbPets = database.use {
            select("Pets").parseList(petParser)
        }

        textView1.text = dbPets.toString()
        /*
        db_pet_recycler.apply {
            adapter = PetRecyclerAdapter(dbPets)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DatabaseActivity)
        }
        */
    }
}