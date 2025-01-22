package com.StavAndYaron.matala2.adapter

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.StavAndYaron.matala2.R
import com.StavAndYaron.matala2.StudentDetailsActivity
import com.StavAndYaron.matala2.model.Student

class StudentViewHolder(itemView: View, private val onClickListener: (Student) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    private var name: TextView? = null
    private var email: TextView? = null
    private var nationalId: TextView? = null
    private var isChecked: CheckBox? = null
    private var student: Student? = null

    init {
        name = itemView.findViewById(R.id.student_row_name)
        email = itemView.findViewById(R.id.student_row_email)
        nationalId = itemView.findViewById(R.id.student_row_national_id)
        isChecked = itemView.findViewById(R.id.student_row_checkbox)

        isChecked!!.apply {
            setOnClickListener {
                student!!.isChecked = (it as CheckBox).isChecked
            }
        }

        itemView.setOnClickListener {
            student?.let { onClickListener(it) }
        }
    }

    fun bind(student: Student, index: Int) {
        this.student = student
        this.name!!.text = student.name
        this.email!!.text = student.email
        this.nationalId!!.text = student.nationalId
        this.isChecked!!.apply {
            isChecked = student.isChecked
            tag = index
        }
    }
}