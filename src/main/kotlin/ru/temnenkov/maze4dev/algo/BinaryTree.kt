package ru.temnenkov.maze4dev.algo

import ru.temnenkov.maze4dev.core.Maze2d
import ru.temnenkov.maze4dev.core.Maze2dArrayImpl
import ru.temnenkov.this4dev.utils.display

fun binaryTree(width: Int, height: Int, seed: Long): Maze2d {

    val random = kotlin.random.Random(seed)
    val output = Maze2dArrayImpl(width, height)

    fun canConnectToNorth(x: Int, y: Int): Boolean {
        return y > 0
    }

    fun canConnectToEast(x: Int, y: Int): Boolean {
        return x < (output.width - 1)
    }

    fun connectToNorth(x: Int, y: Int) {
        check(output.connect(x, y, x, y - 1))
    }

    fun connectToEast(x: Int, y: Int) {
        check(output.connect(x, y, x + 1, y))
    }

    for (x in 0 until output.width) {
        for (y in 0 until output.height) {
            System.err.println("Before $x, $y, \n${output.display()}")
            val canConnectToNorth = canConnectToNorth(x, y)
            val canConnectToEast = canConnectToEast(x, y)
            when {
                canConnectToNorth && canConnectToEast -> when (random.nextInt(1)) {
                    0 -> connectToNorth(x, y)
                    else -> connectToEast(x, y)
                }
                canConnectToNorth -> connectToNorth(x, y)
                canConnectToEast -> connectToEast(x, y)
            }
            System.err.println("After $x, $y, \n${output.display()}")
        }
    }

    return output;
}