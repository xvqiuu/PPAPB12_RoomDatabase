package com.example.students

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.students.databinding.ActivityAddBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AddActivity : AppCompatActivity() {

    private lateinit var mStudentDao: StudentDao
    private lateinit var executorService: ExecutorService
    private lateinit var binding : ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        executorService = Executors.newSingleThreadExecutor()
        val db = StudentRoomDatabase.getDatabase(this)
        mStudentDao = db!!.StudentDao()!!


        with(binding) {
            btnAdd.setOnClickListener {
                insert(
                    Student(
                        nama = addNama.text.toString(),
                        nim = addNim.text.toString(),
                        jurusan = addJurusan.text.toString(),
                        semester = addSemester.text.toString(),
                        asal = addAsal.text.toString()
                    )
                )
                Toast.makeText(this@AddActivity, "Berhasil Menambah Data",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
            btnUndo.setOnClickListener {
                onBackPressed()
            }
        }
    }
    private fun insert(student : Student) {
        executorService.execute {
            mStudentDao.insert(student)
        }
    }
}