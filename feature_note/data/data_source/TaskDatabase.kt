package com.example.kiraku_todo_owo.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kiraku_todo_owo.feature_note.domain.model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase:RoomDatabase(){
    abstract val todoDao: TaskDao
    companion object {
        const val DATABASE_NAME = "tasks_db"
    }
}