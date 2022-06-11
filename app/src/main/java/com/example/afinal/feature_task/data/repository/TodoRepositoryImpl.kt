package com.example.afinal.feature_task.data.repository

import com.example.afinal.feature_task.data.datasource.TodoDao
import com.example.afinal.feature_task.domain.model.Todo
import com.example.afinal.feature_task.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl (
    private val dao: TodoDao
    ): TodoRepository {
    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }

    override suspend fun getTodosByTaskId(taskId: Int): List<Todo>? {
        return dao.getTodosByTaskId(taskId)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override suspend fun insertTodo(todo: Todo) {
        return dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(id: Int) {
        return dao.deleteTodo(id)
    }

    override suspend fun deleteTodoByTaskId(taskId: Int) {
        return dao.deleteTodoByTaskId(taskId)
    }

    override suspend fun getTodoByDate(date: String): List<Todo>? {
        return dao.getTodoByDate(date)
    }
}