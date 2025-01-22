package com.StavAndYaron.matala2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.StavAndYaron.matala2.model.Model
import com.StavAndYaron.matala2.model.Student

class StudentDetailsActivity : AppCompatActivity() {
    private fun initBackButton() {
        val button: Button = findViewById(R.id.student_details_back_button)
        button.setOnClickListener {
            finish()
        }
    }

    private fun initEditButton() {
        findViewById<Button>(R.id.student_details_activity_edit_button).apply {
            setOnClickListener {
                Log.d("todo", "start edit student activity")
            }
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

        initBackButton()

        // Find views
        val nameTextView: TextView = findViewById(R.id.student_details_name)
        val emailTextView: TextView = findViewById(R.id.student_details_email)
        val nationalIdTextView: TextView = findViewById(R.id.student_details_national_id)

        // Set the text views with student data
        nameTextView.text = intent.getStringExtra("studentName")
        emailTextView.text = intent.getStringExtra("studentEmail")
        nationalIdTextView.text = intent.getStringExtra("studentNationalId")
    }

    companion object {
        fun startActivity(context: Context, student: Student) {
            val intent = Intent(context, StudentDetailsActivity::class.java).apply {
                putExtra("studentName", student.name)
                putExtra("studentEmail", student.email)
                putExtra("studentNationalId", student.nationalId)
            }
            context.startActivity(intent)
        }
    }
}