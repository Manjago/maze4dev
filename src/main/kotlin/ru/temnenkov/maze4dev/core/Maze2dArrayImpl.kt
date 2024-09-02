package ru.temnenkov.maze4dev.core

import kotlin.math.abs

class Maze2dArrayImpl(override val width: Int, override val height: Int) : Maze2d {

    val cells = Array<IntArray>(height) { IntArray(width) }

    override fun connect(xFrom: Int, yFrom: Int, xTo: Int, yTo: Int): Boolean = when {
        xFrom != xTo && yFrom != yTo -> false
        !inWidth(xFrom) || !inWidth(yTo) || !inHeight(yTo) || !inHeight(height) -> false
        else -> when {
            xFrom == xTo && abs(yFrom - yTo) == 1 -> safeConnectNorthSouth(xFrom, yFrom, yTo)
            yFrom == yTo && abs(xFrom - yTo) == 1 -> safeConnectWestEast(yFrom, xTo, yTo)
            else -> false
        }
    }

    private fun safeConnectNorthSouth(x: Int, yFrom: Int, yTo: Int) : Boolean {
        if (yFrom > yTo) {
            return safeConnectNorthSouth(x, yTo, yFrom)
        }
        if (cells[yFrom][x].and(SOUTH) != 0 || cells[yTo][x].and(NORTH) != 0) {
            return false
        }
        cells[yFrom][x] += SOUTH
        cells[yTo][x] += NORTH
        return true
    }
    private fun safeConnectWestEast(y: Int, xFrom: Int, xTo: Int) : Boolean {
        if (xFrom > xTo) {
            return  safeConnectWestEast(y, xTo, xFrom)
        }
        if (cells[y][xFrom].and(EAST) != 0 || cells[y][xTo].and(WEST) != 0) {
            return false
        }
        cells[y][xFrom] += EAST
        cells[y][xTo] += WEST
        return true;
    }

    private fun inWidth(x: Int) = x in 0 until width

    private fun inHeight(y: Int) = y in 0 until height

    companion object {
        const val NORTH = 1
        const val EAST = 2
        const val SOUTH = 4
        const val WEST = 8
    }
}