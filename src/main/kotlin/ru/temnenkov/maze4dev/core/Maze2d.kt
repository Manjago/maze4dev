package ru.temnenkov.maze4dev.core

interface Maze2d {
   val width: Int
   val height: Int
   fun connect(xFrom: Int, yFrom: Int, xTo: Int, yTo: Int): Boolean
}