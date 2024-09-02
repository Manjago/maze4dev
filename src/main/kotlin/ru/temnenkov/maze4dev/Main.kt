package ru.temnenkov.maze4dev

import ru.temnenkov.maze4dev.algo.binaryTree
import ru.temnenkov.maze4dev.core.Maze2dArrayImpl
import ru.temnenkov.maze4dev.utils.display
import java.time.Instant

fun main() {
    val seed = Instant.now().toEpochMilli()
    println("seed = $seed")
    val binaryTree = binaryTree(6, 4, seed)
    println(binaryTree.display())


    val string = """6EEEA8
555500
555500
111100"""
    val maze = Maze2dArrayImpl.parse(string)
    check("$string\n" == maze.toString())
}