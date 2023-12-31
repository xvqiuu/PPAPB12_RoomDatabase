package com.example.students

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.students.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    //menyimpan instance dari DAO (Data Access Object)
    private lateinit var mStudentDao: StudentDao
    //digunakan untuk menjalankan tugas secara tidak langsung pada thread terpisah.
    private lateinit var executorService: ExecutorService
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mengatur tata letak menggunakan binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //mendapatkan instance dari database dan DAO menggunakan StudentRoomDatabase
        executorService = Executors.newSingleThreadExecutor()
        val db = StudentRoomDatabase.getDatabase(this)
        mStudentDao = db!!.StudentDao()!!

        with(binding){
            fabAdd.setOnClickListener {
                val intent = Intent(this@MainActivity, AddActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun getAllStudents() {
        //memperbarui tampilan recycler view dengan StudentAdapter
        mStudentDao.allStudents.observe(this) {
            binding.rvStudents.apply {
                adapter = StudentAdapter(it, { student ->
                    val intent = Intent(this@MainActivity, EditActivity::class.java)
                    intent.putExtra("id",student.id)
                    intent.putExtra("nama",student.nama)
                    intent.putExtra("nim",student.nim)
                    intent.putExtra("jurusan",student.jurusan)
                    intent.putExtra("semester",student.semester)
                    intent.putExtra("asal",student.asal)
                    startActivity(intent)
                }, {
                    student ->
                    delete(student)
                })
            }

            // Set adapter dan layout manager pada RecyclerView
            with(binding){
                rvStudents.apply {
                    layoutManager = LinearLayoutManager(context)
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        getAllStudents()
    }

    private fun delete(student: Student){
        executorService.execute{
            mStudentDao.delete(student)
        }
    }

}