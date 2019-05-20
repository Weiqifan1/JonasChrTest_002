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

class Cat(id: Int, name: String, alive: Boolean = true, var livesLeft: Int = 9) : Pet(id, name, alive) {
    companion object {
        val LIVES_LEFT = "livesLeft"
    }
    override val description: String
        get() = "This is the cat $name, who still has $livesLeft lives"

    override fun kill() {
        if (livesLeft > 0) livesLeft--
        if (livesLeft == 0) alive = false
    }
}

class Dog(id: Int, name: String, alive: Boolean = true, val barkPitch: String) : Pet(id, name, alive) {
    companion object {
        val BARK_PITCH = "barkPitch"
    }
    override val description: String
        get() = "The dog named $name barks at $barkPitch"
}

val pets = mutableListOf(
    Cat(1, "Felix"),
    Dog(2, "Rufus", barkPitch = "C4"),
    Dog(3, "Kvast V", barkPitch = "D3"),
    Pet(4, "Killer"),
    Cat(5, "Pusser", livesLeft = 5),
    Cat(6, "Pelle Haleløs")
)