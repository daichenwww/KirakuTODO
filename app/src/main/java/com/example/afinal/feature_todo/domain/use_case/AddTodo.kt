package com.example.afinal.feature_todo.domain.use_case

import com.example.afinal.feature_todo.domain.model.Todo
import com.example.afinal.feature_todo.domain.repository.TodoRepository

class AddTodo (private val repository: TodoRepository){
    suspend operator fun invoke(todo: Todo) {
        repository.insertTodo(todo)
    }
}