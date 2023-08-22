package com.example.memory.model

enum class BoardSize(val numCards: Int) {
    EASY(8),
    MEDIUM(18),
    HARD(24);

    fun getWidth() : Int {
        val value : Int = when(this) {
            EASY -> 2
            MEDIUM -> 3
            HARD -> 4
        }
        return value
    }

    fun getHeight() : Int{
        return numCards / getWidth()
    }

    fun getNumPairs() : Int {
        return numCards / 2
    }
}