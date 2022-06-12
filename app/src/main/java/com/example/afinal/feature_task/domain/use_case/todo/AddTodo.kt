package com.example.afinal.feature_task.domain.use_case.todo

import com.example.afinal.feature_task.domain.model.Todo
import com.example.afinal.feature_task.domain.repository.TodoRepository

class AddTodo (private val repository: TodoRepository){
    suspend operator fun invoke(todo: Todo) {
        repository.insertTodo(todo)
    }
}