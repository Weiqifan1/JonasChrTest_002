package com.example.jonaschrtest_002

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager


import kotlinx.android.synthetic.main.activity_database.*
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast


class DatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        }

    override fun onResume() {
        super.onResume()
        val dbPets = database.use {
            select("Pets").parseList(petParser)
            }
        db_pet_recycler.apply {
            adapter = PetRecyclerAdapter(dbPets)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DatabaseActivity)
            }
        }
    }
