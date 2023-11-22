package com.example.students

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.students.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val data = intent.extras

        with(binding) {
            descNama.text = "${data?.getString("nama")}"
            descNim.text = "${data?.getString("nim")}"
            descJurusan.text = "${data?.getString("jurusan")}"
            descSemester.text = "${data?.getString("semester")}"
            descAsal.text = "${data?.getString("asal")}"

            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}