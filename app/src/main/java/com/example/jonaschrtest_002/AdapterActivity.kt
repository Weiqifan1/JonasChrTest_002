package com.example.jonaschrtest_002

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.jonaschrtest_002.R
import kotlinx.android.synthetic.main.activity_simple.*
import kotlinx.android.synthetic.main.pet_item.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.toast

class AdapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        val petAdapter = PetListAdapter(this, pets)
        pet_list.adapter = petAdapter
        pet_list.setOnItemClickListener { adapterView, view, position, id ->
            val pet = pet_list.getItemAtPosition(position)
            when (pet) {
                is Cat -> toast("Cat selected")
                is Dog -> toast("Dog selected")
                is Pet -> toast("Is it a rabbit?")
                }
            }
        pet_list.setOnItemLongClickListener { adapterView, view, position, id ->
            val pet = pet_list.getItemAtPosition(position)
            when (pet) {
                is Cat -> {
                    pet.kill()
                    toast("Auch")
                    petAdapter.notifyDataSetChanged()
                    }
                }
            true
            }
        }


    }

class PetListAdapter(val context: Context, val pets: List<Pet>) : BaseAdapter() {

    override fun getView(
            position: Int,
            template: View?,
            parent: ViewGroup
            ): View {
        val view = template ?: LayoutInflater
            .from(context)
            .inflate(R.layout.pet_item, parent, false)
        val pet = pets[position]
        if (pet.alive) view.backgroundColor = Color.WHITE
        else view.backgroundColor = Color.RED
        view.name_label.text = pet.name
        view.description_label.text = pet.description
        return view
        }

    override fun getItem(position: Int) =  pets[position]

    override fun getItemId(position: Int) = 0L

    override fun getCount() = pets.size

    }

