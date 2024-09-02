package ru.temnenkov.maze4dev

import jdk.internal.org.jline.keymap.KeyMap.display
import ru.temnenkov.maze4dev.algo.binaryTree
import ru.temnenkov.maze4dev.core.Maze2dArrayImpl
import ru.temnenkov.this4dev.utils.display
import java.time.Instant

fun main() {
    val seed = Instant.now().toEpochMilli()
    println("seed = $seed")
    val binaryTree = binaryTree(6, 4, seed)
    println(binaryTree.display())


/*
    val maze = Maze2dArrayImpl.parse("""6EEEA8
555500
555500
111100""")
    println(maze.display())
*/

}