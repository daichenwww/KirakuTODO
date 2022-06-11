package com.example.afinal.feature_todo.domain.repository

import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_todo.domain.model.TaskWithTodos
import com.example.afinal.feature_todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<Todo>>
    suspend fun getTodoById(todoId: Int): Todo?
//    suspend fun getTodosByTask(task: Task): Flow<List<TaskWithTodos>>
    suspend fun insertTodo(todo: Todo)
    suspend fun deleteTodo(todoId: Int)

}