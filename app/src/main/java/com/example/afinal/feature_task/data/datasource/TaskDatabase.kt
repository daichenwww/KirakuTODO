package com.example.afinal.feature_task.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.afinal.feature_task.domain.model.Task

@Database(entities = [Task::class], version = 3)
abstract class TaskDatabase: RoomDatabase(){
    abstract val taskDao: TaskDao
    companion object {
        const val DATABASE_NAME = "tasks_db"
    }
}