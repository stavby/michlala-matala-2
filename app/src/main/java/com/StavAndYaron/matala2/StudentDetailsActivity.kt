package com.StavAndYaron.matala2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.StavAndYaron.matala2.model.Model
import com.StavAndYaron.matala2.model.Student

class StudentDetailsActivity : AppCompatActivity() {
    private var student: Student? = null
    private var index: Int? = null
    private var size: Int? = null

    private fun initStudent() {
        val name = intent.getStringExtra("studentName")
        val email = intent.getStringExtra("studentEmail")
        val nationalId = intent.getStringExtra("studentNationalId")
        val isChecked = intent.getBooleanExtra("studentIsChecked", false)
        index = intent.getIntExtra("studentIndex", 0)
        student = Student(name!!, email!!, nationalId!!, isChecked)
    }

    private fun initBackButton() {
        val button: Button = findViewById(R.id.student_details_back_button)
        button.setOnClickListener {
            finish()
        }
    }

    private fun initEditButton() {
        findViewById<Button>(R.id.student_details_activity_edit_button).apply {
            setOnClickListener {
                EditStudentActivity.startActivity(this@StudentDetailsActivity, student!!, index!!)
            }
        }
    }

    private fun initDetails() {
        findViewById<TextView>(R.id.student_details_name).apply {
            text = student!!.name
        }
        findViewById<TextView>(R.id.student_details_email).apply {
            text = student!!.email
        }
        findViewById<TextView>(R.id.student_details_national_id).apply {
            text = student!!.nationalId
        }
        findViewById<CheckBox>(R.id.student_details_is_checked).apply {
            isChecked = student!!.isChecked
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initStudent()
        initBackButton()
        initEditButton()
        initDetails()
    }

    override fun onPause() {
        super.onPause()
        size = Model.instance.students.size
    }

    override fun onResume() {
        super.onResume()
        if (size != null && Model.instance.students.size != size) {
            finish()
        }
        student = Model.instance.students[index!!]
        initDetails()
    }

    companion object {
        fun startActivity(context: Context, student: Student, index: Int) {
            val intent = Intent(context, StudentDetailsActivity::class.java).apply {
                putExtra("studentName", student.name)
                putExtra("studentEmail", student.email)
                putExtra("studentNationalId", student.nationalId)
                putExtra("studentIsChecked", student.isChecked)
                putExtra("studentIndex", index)
            }
            context.startActivity(intent)
        }
    }
}