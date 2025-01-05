package com.StavAndYaron.matala2.model

class Model private constructor() {
    val students: MutableList<Student> = ArrayList()

    companion object {
        val instance = Model()
    }

    init {
        for (i in 1..20) {
            students.add(
                Student(
                    name = "Stav $i",
                    email = "stav$i@gmail.com",
                    nationalId = i.toString().padStart(8, '0'),
                    isChecked = false
                )
            )
        }
    }
}