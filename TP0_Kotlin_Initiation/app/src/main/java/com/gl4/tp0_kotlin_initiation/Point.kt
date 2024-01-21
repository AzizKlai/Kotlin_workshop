package com.gl4.tp0_kotlin_initiation

import kotlin.math.abs

data class Point(val x : Int, val y: Int =0) {
    fun distance( p:Point)= abs(this.x - p.x) + abs(this.y-p.y)

}