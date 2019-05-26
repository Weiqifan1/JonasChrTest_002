package com.example.jonaschrtest_002.Database


open class Pet(val id: Int, var name: String, var alive: Boolean = true) {
    companion object {
        val TABLE_NAME = "Pets"
        val DTYPE = "dtype"
        val ID = "id"
        val NAME = "name"
        val ALIVE = "alive"
    }
    open val description: String
        get() = "A pet called $name probably a rabbit"
    open fun kill() { alive = false }
}
