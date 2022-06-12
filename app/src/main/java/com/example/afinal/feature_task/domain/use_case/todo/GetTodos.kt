package com.example.afinal.feature_task.domain.use_case.todo

import com.example.afinal.feature_task.domain.model.Todo
import com.example.afinal.feature_task.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTodos (private val repository: TodoRepository) {
    operator fun invoke(): Flow<List<Todo>> {
        return repository.getTodos().map { todos ->
            todos.sortedWith(compareBy({ it.dueDate }, { it.done }))}
    }
}