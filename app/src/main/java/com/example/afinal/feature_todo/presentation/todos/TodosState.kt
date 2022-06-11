package com.example.afinal.feature_todo.presentation.todos

import com.example.afinal.feature_todo.domain.model.Todo

data class TodosState(
    val todos: List<Todo> = emptyList(),
    val grouped: Map<String, List<Todo>> = emptyMap()
)