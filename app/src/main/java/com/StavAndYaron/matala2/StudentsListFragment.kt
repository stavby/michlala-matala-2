package com.StavAndYaron.matala2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.StavAndYaron.matala2.adapter.OnStudentItemClickListener
import com.StavAndYaron.matala2.adapter.StudentsAdapter
import com.StavAndYaron.matala2.model.Model
import com.StavAndYaron.matala2.model.Student

class StudentsListFragment : Fragment() {

    private var students: MutableList<Student>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_students_list, container, false)

        students = Model.instance.students
        val linearLayoutManager = LinearLayoutManager(context)

        view.findViewById<RecyclerView>(R.id.students_list_fragment_recycler_view).apply {
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

        return view
    }

}