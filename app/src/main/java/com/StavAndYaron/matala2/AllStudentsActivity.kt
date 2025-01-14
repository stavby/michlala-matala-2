package com.StavAndYaron.matala2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.StavAndYaron.matala2.adapter.OnStudentItemClickListener
import com.StavAndYaron.matala2.adapter.StudentsAdapter
import com.StavAndYaron.matala2.model.Model
import com.StavAndYaron.matala2.model.Student

class AllStudentsActivity : AppCompatActivity() {
    private var students: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_all_students)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        students = Model.instance.students
        val linearLayoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.students_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = StudentsAdapter(students!!).apply {
                onClickListener = object : OnStudentItemClickListener {
                    override fun onItemClick(student: Student) {
                        Log.d("TAG", "Clicked on ${student.name}")
                    }
                }
            }
        }
    }
}