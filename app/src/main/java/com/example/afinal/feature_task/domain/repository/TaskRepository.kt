package com.example.afinal.feature_task.domain.repository

import com.example.afinal.feature_task.domain.model.Task
import kotlinx.coroutines.flow.Flow

// Repository in domain: interface def. Repository in data: implementation.
interface TaskRepository {
    fun getTasks(): Flow<List<Task>>

    suspend fun getTaskById(id: Int): Task?

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Int)

    // edited by: zshzzz
    suspend fun getDoneTaskNumberInRange(start: String, end: String): Int

    suspend fun getDoneTaskNumber(): Int
}