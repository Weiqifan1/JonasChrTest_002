package com.example.jonaschrtest_002

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_pet.*
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import org.jetbrains.anko.sdk27.coroutines.onClick

class EditPetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pet)
        val id = intent.extras.getInt("id")
        val pet = database.use {
            select(Pet.TABLE_NAME)
                .whereArgs("id = {id}", "id" to id)
                .parseSingle(petParser)
            }
        id_text.text = pet.id.toString()
        name_edit.setText(pet.name)

        update_button.onClick {
            // val id = id_text.text.toString().toInt()
            val name = name_edit.text.toString()
            database.use {
                update(Pet.TABLE_NAME, Pet.NAME to name)
                    // .whereArgs("id = {id}", Pair("id", id))
                    // .whereSimple("id = ?", id.toString())
                    .whereArgs("id = {id}", "id" to id)
                    .exec()
                }
            finish()
            }
    }
}
