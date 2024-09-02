package ru.temnenkov.maze4dev

import ru.temnenkov.maze4dev.algo.binaryTree
import ru.temnenkov.maze4dev.core.Maze2dArrayImpl
import ru.temnenkov.this4dev.utils.display

fun main() {
    println(Maze2dArrayImpl(6, 4).display())
    val binaryTree = binaryTree(6, 4)
    println(binaryTree.display())
}