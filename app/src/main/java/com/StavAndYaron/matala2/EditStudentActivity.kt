package com.StavAndYaron.matala2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.StavAndYaron.matala2.model.Model
import com.StavAndYaron.matala2.model.Student

class EditStudentActivity : AppCompatActivity() {
    private var student: Student? = null
    private var index: Int? = null

    private fun initStudent() {
        val name = intent.getStringExtra("studentName")
        val email = intent.getStringExtra("studentEmail")
        val nationalId = intent.getStringExtra("studentNationalId")
        val isChecked = intent.getBooleanExtra("studentIsChecked", false)
        index = intent.getIntExtra("studentIndex", 0)
        student = Student(name!!, email!!, nationalId!!, isChecked)
    }

    private fun initBackButton() {
        findViewById<Button>(R.id.edit_student_back_button).apply {
            setOnClickListener {
                finish()
            }
        }
    }

    private fun initCancelButton() {
        findViewById<Button>(R.id.edit_student_activity_cancel_button).apply {
            setOnClickListener {
                finish()
            }
        }
    }

    private fun initSubmitButton() {
        findViewById<Button>(R.id.edit_student_activity_submit_button).apply {
            setOnClickListener {
                val name = findViewById<TextView>(R.id.edit_student_name_field).text.toString()
                val email = findViewById<TextView>(R.id.edit_student_email_field).text.toString()
                val nationalId = findViewById<TextView>(R.id.edit_student_national_id_field).text.toString()
                val isChecked = findViewById<CheckBox>(R.id.edit_student_is_checked_field).isChecked

                val isValid = name.isNotEmpty() && email.isNotEmpty() && nationalId.isNotEmpty()

                if (isValid) {
                    Model.instance.students[intent.getIntExtra("studentIndex", 0)] =
                        Student(name, email, nationalId, isChecked)
                    finish()
                } else {
                    Toast.makeText(this@EditStudentActivity, "All values are required", Toast.LENGTH_SHORT ).show()
                }
            }
        }
    }

    private fun initDeleteButton () {
        findViewById<Button>(R.id.edit_student_activity_delete_button).apply {
            setOnClickListener {
                Model.instance.students.removeAt(index!!)
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initStudent()
        initBackButton()
        initCancelButton()
        initSubmitButton()
        initDeleteButton()

        findViewById<TextView>(R.id.edit_student_name_field).text = student!!.name
        findViewById<TextView>(R.id.edit_student_email_field).text = student!!.email
        findViewById<TextView>(R.id.edit_student_national_id_field).text = student!!.nationalId
        findViewById<CheckBox>(R.id.edit_student_is_checked_field).isChecked = student!!.isChecked
    }

    companion object {
        fun startActivity(context: Context, student: Student, index: Int) {
            val intent = Intent(context, EditStudentActivity::class.java).apply {
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