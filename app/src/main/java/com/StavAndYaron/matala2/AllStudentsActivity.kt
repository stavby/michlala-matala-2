package com.StavAndYaron.matala2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        findViewById<Button>(R.id.all_students_activity_new_student_button).apply {
            setOnClickListener {
                CreateStudentActivity.startActivity(this@AllStudentsActivity)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        students = Model.instance.students
        val linearLayoutManager = LinearLayoutManager(this)

        findViewById<RecyclerView>(R.id.students_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = StudentsAdapter(students!!).apply {
                setClickListener { student ->
                    StudentDetailsActivity.startActivity(this@AllStudentsActivity, student)
                }
            }
        }
    }
}