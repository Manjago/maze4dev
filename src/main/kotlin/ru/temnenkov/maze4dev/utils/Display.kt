package ru.temnenkov.this4dev.utils

import ru.temnenkov.maze4dev.core.Maze2d

fun Maze2d.display(): String {

    val sb = StringBuilder()

    fun printTopRow(y: Int) {
        for (x in 0 until this.width) {
            if (this.isConnected(x, y, x, y - 1)) {
                sb.append("+  ")
            } else {
                sb.append("+--")
            }
        }
        sb.append("+\n")
    }

    fun printMiddleRow(y: Int) {
        for (x in 0 until this.width) {
            if (this.isConnected(x, y, x + 1, y)) {
                sb.append("   ")
            } else {
                sb.append("|  ")
            }
            if (x == this.width - 1) {
                if (this.isConnected(x, y, x - 1, y)) {
                    sb.append(" \n")
                } else {
                    sb.append("|\n")
                }
            }
        }
    }

    fun printBottomRow(y: Int) {
        for (x in 0 until this.width) {
            if (this.isConnected(x, y, x, y + 1)) {
                sb.append("+  ")
            } else {
                sb.append("+--")
            }
        }
        sb.append("+\n")
    }

    for (y in 0..this.height - 1) {
        printTopRow(y)
        printMiddleRow(y)
        if (y == this.height - 1) {
            printBottomRow(y)
        }
    }

    return sb.toString()
}