package ru.temnenkov.maze4dev

import ru.temnenkov.maze4dev.algo.binaryTree
import ru.temnenkov.maze4dev.core.Maze2dArrayImpl
import ru.temnenkov.this4dev.utils.display

fun main() {
    val binaryTree = binaryTree(6, 4, 0)
    println(binaryTree.display())
}