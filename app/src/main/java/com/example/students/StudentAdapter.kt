package com.example.students

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.students.databinding.ItemStudentBinding
import java.nio.file.Files.delete
import java.util.concurrent.ExecutorService

typealias onClickUpdate = (Student) -> Unit
typealias onClickDelete = (Student) -> Unit

class StudentAdapter(private val listStudents: List<Student>,
                     private val onClickUpdate : onClickUpdate,
                     private val onClickDelete: onClickDelete):
    RecyclerView.Adapter<StudentAdapter.ItemStudentViewHolder>() {

    inner class ItemStudentViewHolder(private val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Student) {
            with(binding) {
                txtName.text = data.nama
                txtNim.text = data.nim

                itemView.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putInt("id", data.id)
                    bundle.putString("nama", data.nama)
                    bundle.putString("nim", data.nim)
                    bundle.putString("jurusan", data.jurusan)
                    bundle.putString("semester", data.semester)
                    bundle.putString("asal", data.asal)

                    Toast.makeText(itemView.context, "You clicked on ${data.nama}",
                        Toast.LENGTH_SHORT).show()

                    val intent = Intent(
                        binding.root.context,
                        DetailActivity::class.java
                    ).apply { putExtras(bundle) }
                    binding.root.context.startActivity(intent)
                }

                edit.setOnClickListener {
                    onClickUpdate(data)
                }

                delete.setOnClickListener {
                    onClickDelete(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemStudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),parent,
            false)
        return ItemStudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listStudents.size
    }

    override fun onBindViewHolder(holder: StudentAdapter.ItemStudentViewHolder, position: Int) {
        holder.bind(listStudents[position])
    }
}