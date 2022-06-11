package com.example.afinal.feature_task.domain.use_case.todo

import com.example.afinal.feature_task.domain.repository.TodoRepository

class DeleteTodo(private val repository: TodoRepository) {
    suspend operator fun invoke(todoId: Int?) {
        if(todoId != null) repository.deleteTodo(todoId)
    }
}