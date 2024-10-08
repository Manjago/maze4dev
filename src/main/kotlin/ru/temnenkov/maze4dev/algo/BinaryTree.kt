package ru.temnenkov.maze4dev.algo

import ru.temnenkov.maze4dev.core.Maze2d
import ru.temnenkov.maze4dev.core.Maze2dArrayImpl
import ru.temnenkov.maze4dev.utils.display
import java.time.Instant

fun binaryTree(width: Int, height: Int, seed: Long = Instant.now().toEpochMilli(), trace: Boolean = false): Maze2d {

    val random = kotlin.random.Random(seed)
    val output: Maze2d = Maze2dArrayImpl(width, height)

    fun canConnectToNorth(x: Int, y: Int) = y > 0

    fun canConnectToEast(x: Int, y: Int) = x < (output.width - 1)

    fun connectToNorth(x: Int, y: Int) {
        check(output.connect(x, y, x, y - 1))
    }

    fun connectToEast(x: Int, y: Int) {
        check(output.connect(x, y, x + 1, y))
    }

    for (x in 0 until output.width) {
        for (y in 0 until output.height) {
            if (trace) {
                System.err.println("Before $x, $y, \n${output.display()}, \n$output")
            }
            val canConnectToNorth = canConnectToNorth(x, y)
            val canConnectToEast = canConnectToEast(x, y)
            when {
                canConnectToNorth && canConnectToEast -> when (random.nextInt(2)) {
                    0 -> connectToNorth(x, y)
                    else -> connectToEast(x, y)
                }
                canConnectToNorth -> connectToNorth(x, y)
                canConnectToEast -> connectToEast(x, y)
            }
            if (trace) {
                System.err.println("After $x, $y, \n${output.display()}, \n$output")
            }
        }
    }

    return output
}