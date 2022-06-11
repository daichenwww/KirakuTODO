package com.example.afinal.feature_todo.data.repository

import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_todo.data.datasource.TodoDao
import com.example.afinal.feature_todo.domain.model.TaskWithTodos
import com.example.afinal.feature_todo.domain.model.Todo
import com.example.afinal.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl (
    private val dao: TodoDao
    ) : TodoRepository {

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }

    override suspend fun getTodoById(todoId: Int): Todo? {
        return dao.getTodoById(todoId)
    }
//
//    override suspend fun getTodosByTask(task: Task): Flow<List<TaskWithTodos>> {
//        return dao.getTodosByTask()
//    }

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todoId: Int) {
        dao.deleteTodo(todoId)
    }
}