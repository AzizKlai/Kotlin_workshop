package com.gl4.tp0_kotlin_initiation

fun main(argv: Array<String>){
    print("hello")
    val rectangles = mutableListOf<Rectangle>(
        Rectangle(), // default values
        Rectangle(Point(12,5)),
        Rectangle(q=Point(12,5)),
        Rectangle(Point(12,5),Point(1,15))
        )

    for ((i, r) in rectangles.withIndex()) {
        print("\n rectangle n°$i : $r , surface: ${r.surface()} ")
    }
}