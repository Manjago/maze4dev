package ru.temnenkov.maze4dev.core

import javax.swing.Spring.height
import kotlin.math.abs

class Maze2dArrayImpl(override val width: Int, override val height: Int) : Maze2d {

    val cells = Array<IntArray>(height) { IntArray(width) }

    override fun connect(xFrom: Int, yFrom: Int, xTo: Int, yTo: Int): Boolean = when {
        isNotNeighbours(xFrom, xTo, yFrom, yTo) -> false
        isNotInBounds(xFrom, xTo, yTo, yFrom) -> false
        else -> when {
            isSame(xFrom, xTo, yFrom, yTo) -> safeConnectNorthSouth(xFrom, yFrom, yTo)
            isSame(yFrom, yTo, xFrom, xTo) -> safeConnectWestEast(yFrom, xFrom, xTo)
            else -> false
        }
    }

    override fun isConnected(xFrom: Int, yFrom: Int, xTo: Int, yTo: Int): Boolean = when {
        isNotNeighbours(xFrom, xTo, yFrom, yTo) -> false
        isNotInBounds(xFrom, xTo, yTo, yFrom) -> false
        else -> when {
            isSame(xFrom, xTo, yFrom, yTo) -> isSafeConnectedNorthSouth(xFrom, yFrom, yTo)
            isSame(yFrom, yTo, xFrom, xTo) -> isSafeConnectedWestEast(yFrom, xFrom, xTo)
            else -> false
        }
    }

    private fun isSame(xFrom: Int, xTo: Int, yFrom: Int, yTo: Int) = xFrom == xTo && abs(yFrom - yTo) == 1

    private fun isNotInBounds(xFrom: Int, xTo: Int, yTo: Int, yFrom: Int) =
        !inWidth(xFrom) || !inWidth(xTo) || !inHeight(yTo) || !inHeight(yFrom)

    private fun isNotNeighbours(xFrom: Int, xTo: Int, yFrom: Int, yTo: Int) = xFrom != xTo && yFrom != yTo

    private fun safeConnectNorthSouth(x: Int, yFrom: Int, yTo: Int): Boolean = when {
        yFrom > yTo -> safeConnectNorthSouth(x, yTo, yFrom)

        isSafeConnectedNorthSouth(x, yFrom, yTo) -> false

        else -> {
            cells[yFrom][x] += SOUTH
            cells[yTo][x] += NORTH
            true
        }
    }

    private fun safeConnectWestEast(y: Int, xFrom: Int, xTo: Int): Boolean = when {
        xFrom > xTo -> safeConnectWestEast(y, xTo, xFrom)

        isSafeConnectedWestEast(y, xFrom, xTo) -> false

        else -> {
            cells[y][xFrom] += EAST
            cells[y][xTo] += WEST
            true
        }
    }

    private fun isSafeConnectedNorthSouth(x: Int, yFrom: Int, yTo: Int): Boolean = if (yFrom > yTo) {
        isSafeConnectedNorthSouth(x, yTo, yFrom)
    } else {
        cells[yFrom][x].and(SOUTH) != 0 || cells[yTo][x].and(NORTH) != 0
    }

    private fun isSafeConnectedWestEast(y: Int, xFrom: Int, xTo: Int): Boolean = if (xFrom > xTo) {
        isSafeConnectedWestEast(y, xTo, xFrom)
    } else {
        cells[y][xFrom].and(EAST) != 0 || cells[y][xTo].and(WEST) != 0
    }

    private fun inWidth(x: Int) = x in 0 until width

    private fun inHeight(y: Int) = y in 0 until height

    companion object {
        const val NORTH = 1
        const val EAST = 2
        const val SOUTH = 4
        const val WEST = 8

        fun parse(string: String) : Maze2dArrayImpl {
            val rows = string.split('\n')
            val height = rows.size
            val width = rows[0].length
            val result = Maze2dArrayImpl(width, height)

            for (y in 0 until height) {
                val charArray = rows[y].toCharArray()
                for (x in 0 until width) {
                    result.cells[y][x] = charArray[x].digitToInt(16)
                }
            }

            return result
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (y in 0 until height) {
            for (x in 0 until width) {
                sb.append(cells[y][x].toString(16).uppercase())
            }
            sb.append("\n")
        }
        return sb.toString()
    }


}