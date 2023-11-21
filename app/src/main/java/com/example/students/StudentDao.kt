package com.example.students

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(student : Student)

    @Update
    fun update(student : Student)

    @Delete
    fun delete(student : Student)

    @get:Query("SELECT * from student_table ORDER BY id ASC")
    val allStudents: LiveData<List<Student>>
}