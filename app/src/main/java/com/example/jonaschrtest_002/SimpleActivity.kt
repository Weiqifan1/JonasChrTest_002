package com.example.jonaschrtest_002

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.jonaschrtest_002.R
import kotlinx.android.synthetic.main.activity_simple.*
import org.jetbrains.anko.longToast

class SimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        val petNames = listOf("Felix", "Rufus", "Kvast V", "Killer", "Pusser", "Pelle Haleløs")
        pet_list.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            petNames
            )
        pet_list.setOnItemClickListener { adapterView, view, position, id ->
            val item = pet_list.getItemAtPosition(position)
            longToast("You clicked item $position: $item")
            }

        }
    }
