package com.StavAndYaron.matala2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.StavAndYaron.matala2.R
import com.StavAndYaron.matala2.model.Student

class StudentsAdapter(private val students: MutableList<Student>) :
    RecyclerView.Adapter<StudentViewHolder>() {
    var onClickListener: OnStudentItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.student_list_row,
                parent,
                false
            ),
            onClickListener
        )
    }

    override fun onBindViewHolder(holder: StudentViewHolder, index: Int) {
        holder.bind(students[index], index)
    }
    
    override fun getItemCount(): Int {
        return students.size
    }

}