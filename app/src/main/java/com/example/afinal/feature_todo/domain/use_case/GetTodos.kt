package com.example.afinal.feature_todo.domain.use_case;

import com.example.afinal.feature_todo.domain.model.Todo
import com.example.afinal.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTodos (private val repository: TodoRepository) {
    operator fun invoke(): Flow<List<Todo>> {
        return repository.getTodos().map { todos -> todos.sortedBy { it.todoPlanDate }}
    }
}