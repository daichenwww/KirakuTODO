package com.example.kiraku_todo_owo.feature_note.domain.repository

import com.example.kiraku_todo_owo.feature_note.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>

    suspend fun getTaskById(id: Int): Task?

    suspend fun insertTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)
}