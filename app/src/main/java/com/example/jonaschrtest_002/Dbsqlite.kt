package com.example.jonaschrtest_002
/*
import android.app.Activity
import android.os.Bundle
import com.example.jonaschrtest_002.Database.database
import com.example.jonaschrtest_002.Database.petParser
import kotlinx.android.synthetic.main.activity_dbsqlite.*
import org.jetbrains.anko.db.select

class Dbsqlite: Activity()  {

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
*/