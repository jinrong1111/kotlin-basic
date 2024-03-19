package com.thoughtworks.kotlin_basic.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NumToColUtilTest {
    private val util = NumToColUtil()
    @Test
    fun `sequence from 1 with count 2`() {
        val expected = arrayOf("A", "B")
        val result = util.numToCol(1, 2)
        assertArrayEquals(expected, result)
    }

    @Test
    fun `sequence from 26 with count 3`() {
        val expected = arrayOf("Z", "AA", "AB")
        val result = util.numToCol(26, 3)
        assertArrayEquals(expected, result)
    }

    @Test
    fun `sequence exceeds ZZZ`() {
        assertThrows(IllegalArgumentException::class.java) {
            util.numToCol(18279, 1)
        }
    }

    @Test
    fun `negative start or count`() {
        assertThrows(IllegalArgumentException::class.java) {
            util.numToCol(-1, -1)
        }
        assertThrows(IllegalArgumentException::class.java) {
            util.numToCol(-1,1)
        }
        assertThrows(IllegalArgumentException::class.java) {
            util.numToCol(1,-1)
        }
    }
}