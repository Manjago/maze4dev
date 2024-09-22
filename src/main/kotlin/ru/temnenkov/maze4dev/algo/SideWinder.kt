package ru.temnenkov.maze4dev.algo

import ru.temnenkov.maze4dev.core.Maze2d
import ru.temnenkov.maze4dev.core.Maze2dArrayImpl
import java.time.Instant

fun sideWinder(width: Int, height: Int, seed: Long = Instant.now().toEpochMilli(), trace: Boolean = false): Maze2d {

    kotlin.random.Random(seed)
    val output: Maze2d = Maze2dArrayImpl(width, height)

    for (x in 0 until output.width) {

        //todo нам надо получше сделать это всё с cell

        for (y in 0 until output.height) {

        }
    }

    TODO()
}