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

class CreateStudentActivity : AppCompatActivity() {
    private fun initBackButton() {
        val button: Button = findViewById(R.id.add_student_back_button)
        button.setOnClickListener {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initBackButton()

        val button: Button = findViewById<Button>(R.id.add_student_activity_submit_button)

        button.setOnClickListener {
            val nameField = findViewById<TextView>(R.id.new_student_name_field)
            val emailField = findViewById<TextView>(R.id.new_student_email_field)
            val nationalIdField = findViewById<TextView>(R.id.new_student_national_id_field)
            val isCheckedField = findViewById<CheckBox>(R.id.new_student_is_checked_field)

            val name = nameField.text.toString()
            val email = emailField.text.toString()
            val nationalId = nationalIdField.text.toString()
            val isChecked = isCheckedField.isChecked

            val isValid = name.isNotEmpty() && email.isNotEmpty() && nationalId.isNotEmpty()

            if (isValid) {
                Model.instance.students.add(Student(name, email, nationalId, isChecked))

                nameField.text = ""
                emailField.text = ""
                nationalIdField.text = ""
                isCheckedField.isChecked = false
            } else {
                Toast.makeText(this, "All values are required", Toast.LENGTH_SHORT ).show()
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, CreateStudentActivity::class.java)
            context.startActivity(intent)
        }
    }
}