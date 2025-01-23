package com.StavAndYaron.matala2.model

class Model private constructor() {
    val students: MutableList<Student> = ArrayList()

    companion object {
        val instance = Model()
    }
}