package com.example.jonaschrtest_002

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.jonaschrtest_002.EditPetActivity.Utils.startNewActivity
import com.example.jonaschrtest_002.Models.Audio
import kotlinx.android.synthetic.main.activity_edit_pet.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import org.jetbrains.anko.sdk27.coroutines.onClick

class EditPetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pet)
        val id = intent.extras.getInt("id")
        val bookMark = database.use {
            select(BookMark.TABLE_NAME2)
                .whereArgs("id = {id}", "id" to id)
                .parseSingle(bookMarkParser)
            }
        id_text.text = bookMark.id.toString()
        name_edit.setText(bookMark.bookMarkName)

        playBookMark.onClick {
            //Audio("none Chosen", "none", "", "", "", ArrayList<Audio>(), "")
            val ourNewAudio = Audio(bookMark.bookPath,
                bookMark.bookMarkName, "", "", "File", ArrayList<Audio>(), "",bookMark.bookTime.toString())

            startNewActivity(
                this@EditPetActivity,
                PlaySound::class.java,
                ourNewAudio
            )
        }

        delete_button.onClick {
            database.use{
                delete(BookMark.TABLE_NAME2, "id = {bookmarkID}", "bookmarkID" to bookMark.id)
            }
            finish()
        }

        update_button.onClick {
            val name = name_edit.text.toString()
            database.use {
                update(BookMark.TABLE_NAME2, BookMark.BOOKMARK_NAME to name)
                    .whereArgs("id = {id}", "id" to id)
                    .exec()
                }
            finish()
            }
    }

    object Utils {
        fun startNewActivity(context: Context, clazz: Class<*>, user:Audio) {

            val intent = Intent(context, clazz)
            intent.putExtra("valor", user.aPath)
            intent.putExtra("valorName" ,user.aName)
            intent.putExtra("valorBookMarkTime", user.bookMarkTime)
            context.startActivity(intent)
        }
    }
}
