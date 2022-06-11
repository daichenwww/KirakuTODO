package com.example.afinal.feature_todo.domain.use_case;

import com.example.afinal.feature_todo.domain.model.Todo
import com.example.afinal.feature_todo.domain.repository.TodoRepository

class GetTodo (private val repository: TodoRepository) {
    suspend operator fun invoke(todoId: Int): Todo? {
        return repository.getTodoById(todoId)
    }
}