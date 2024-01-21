package com.gl4.tp0_kotlin_initiation

import kotlin.math.abs

class Rectangle(val p:Point=Point(0,0),val q:Point=Point(1,1)) {
    override fun toString(): String {
        return "p=$p , q=$q"
    }
    fun surface()= abs(this.p.x-this.q.x)*abs(p.y-q.y)

}