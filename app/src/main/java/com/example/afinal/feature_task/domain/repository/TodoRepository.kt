package com.example.afinal.feature_task.domain.repository

import com.example.afinal.feature_task.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<Todo>>
    suspend fun getTodosByTaskId(taskId: Int): List<Todo>?
    suspend fun getTodoById(id: Int): Todo?
    suspend fun insertTodo(todo: Todo)
    suspend fun deleteTodo(id: Int)
    suspend fun deleteTodoByTaskId(taskId: Int)
    suspend fun getTodoByDate(date: String): List<Todo>?

    suspend fun getDoneTodoNumberInRange(start: String, end: String): Int

    suspend fun getDoneTodoNumber(): Int
}