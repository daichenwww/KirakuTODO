package com.example.afinal.feature_task.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.domain.model.Todo

@Database(
    entities = [
        Task::class,
        Todo::class
    ],
    version = 1)
abstract class KirakuDatabase: RoomDatabase(){
    abstract fun taskDao(): TaskDao
    abstract fun todoDao(): TodoDao
}