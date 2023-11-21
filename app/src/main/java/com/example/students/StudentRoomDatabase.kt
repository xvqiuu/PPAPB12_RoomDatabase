package com.example.students

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class],
    version = 1,
    exportSchema = false)
abstract class StudentRoomDatabase : RoomDatabase() {
    abstract fun StudentDao() : StudentDao?

    companion object {
        @Volatile
        private var INSTANCE : StudentRoomDatabase ? = null
        fun getDatabase(context: Context) : StudentRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(StudentRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        StudentRoomDatabase::class.java, "student_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

}