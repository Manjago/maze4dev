package ru.temnenkov.maze4dev.algo

import ru.temnenkov.maze4dev.core.Maze2d
import ru.temnenkov.maze4dev.core.Maze2dArrayImpl

fun binaryTree(width: Int, height: Int): Maze2d {

    val output = Maze2dArrayImpl(width, height)

    fun canConnectToNorth(x: Int, y: Int): Boolean {
        return x > 0
    }

    fun canConnectToEast(x: Int, y: Int): Boolean {
        return y < (output.width - 1)
    }

    fun connectToNorth(x: Int, y: Int) {
        output.connect(x, y, x - 1, y)
    }

    fun connectToEast(x: Int, y: Int) {
        output.connect(x, y, x, y + 1)
    }

    for (x in 0 until output.width) {
        for (y in 0 until output.height) {
            val canConnectToNorth = canConnectToNorth(x, y)
            val canConnectToEast = canConnectToEast(x, y)
            when {
                canConnectToNorth && canConnectToEast -> when (kotlin.random.Random.nextInt(1)) {
                    0 -> connectToNorth(x, y)
                    else -> connectToEast(x, y)
                }
                canConnectToNorth -> connectToNorth(x, y)
                canConnectToEast -> connectToEast(x, y)
            }
        }
    }

    return output;
}