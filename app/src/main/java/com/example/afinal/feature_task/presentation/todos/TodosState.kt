package com.example.afinal.feature_task.presentation.todos

import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.domain.model.Todo

data class TodosState(
    val todos: List<Todo> = emptyList(),
    val groupByMY: Map<String, List<Todo>> = emptyMap()
)