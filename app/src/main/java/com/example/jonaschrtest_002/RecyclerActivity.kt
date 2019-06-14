package com.example.jonaschrtest_002

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_recycler.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import org.jetbrains.anko.startActivity

class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        pet_recycler.apply {
            adapter = PetRecyclerAdapter(pets)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@RecyclerActivity)
            }
        }
    }

class PetRecyclerAdapter(val pets: List<Pet>) :
        RecyclerView.Adapter<PetRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.pet_item, parent, false)
        view.onClick {
            val name = view.findViewById<TextView>(R.id.name_label).text
            val id = view.getTag()
            parent.context.startActivity<EditPetActivity>("id" to id)
            this@PetRecyclerAdapter.notifyDataSetChanged()
            }
        return ViewHolder(view)
        }

    override fun getItemCount() = pets.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.view
        val name_label = view.findViewById<TextView>(R.id.name_label)
        val description_label = view.findViewById<TextView>(R.id.description_label)
        val pet = pets[position]
        view.setTag(pet.id)
        //view.setTag(2, pet.javaClass.name)
        name_label.text = pet.name
        description_label.text = pet.description
        }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    }