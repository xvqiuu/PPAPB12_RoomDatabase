package com.example.students

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "student_table")
data class Student (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,

    @ColumnInfo(name = "nama")
    val nama: String,

    @ColumnInfo(name = "nim")
    val nim: String,

    @ColumnInfo(name = "jurusan")
    val jurusan: String,

    @ColumnInfo(name = "semester")
    val semester: String,

    @ColumnInfo(name = "asal")
    val asal: String,

): Serializable
