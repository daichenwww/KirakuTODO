package com.example.afinal.feature_todo.domain.use_case

import com.example.afinal.feature_todo.domain.repository.TodoRepository

class DeleteTodo(private val repository: TodoRepository) {
    suspend operator fun invoke(todoId: Int?) {
        if(todoId != null) repository.deleteTodo(todoId)
    }
}