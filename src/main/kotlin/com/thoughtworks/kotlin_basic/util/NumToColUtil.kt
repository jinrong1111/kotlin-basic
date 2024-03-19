package com.thoughtworks.kotlin_basic.util

import java.lang.IllegalArgumentException

class NumToColUtil {
    fun numToCol(start: Int, count: Int): Array<String> {
        if(start <= 0 || count <= 0){
            throw IllegalArgumentException("error argument!!")
        }
        val result = mutableListOf<String>()
        for (i in 0 until count) {
            val num = start + i
            val column = StringBuilder()
            var remainder = num
            while (remainder > 0) {
                val quotient = (remainder - 1) / 26
                val r = (remainder - 1) % 26
                column.insert(0, ('A'.code + r).toChar())
                remainder = quotient
            }
            if (column.length > 3) {
                throw IllegalArgumentException("error!! exceeds ZZZ!!")
            }
            result.add(column.toString())
        }
        return result.toTypedArray()
    }
}