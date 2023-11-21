package com.example.students

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.students.databinding.ActivityEditBinding
import java.util.concurrent.ExecutorService

class EditActivity : AppCompatActivity() {

    private lateinit var mStudentDao: StudentDao
    private lateinit var executorService: ExecutorService
    private var updateId : Int = 0

    private lateinit var binding : ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            edtNama.setText(intent.getStringExtra("title"))
            edtNim.setText(intent.getStringExtra("nim"))
            edtJurusan.setText(intent.getStringExtra("jurusan"))
            edtSemester.setText(intent.getStringExtra("semester"))
            edtAsal.setText(intent.getStringExtra("asal"))
            btnEdit.setOnClickListener {
                update(
                    Student(
                    id = updateId,
                    nama = edtNama.text.toString(),
                    nim = edtNim.text.toString(),
                    jurusan = edtJurusan.text.toString(),
                    semester = edtSemester.text.toString(),
                    asal = edtAsal.text.toString()
                ))
                finish()

                Toast.makeText(this@EditActivity, "Data Berhasil Disimpan",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun update(student: Student) {
        executorService.execute {
            mStudentDao.insert(student)
        }

    }

}