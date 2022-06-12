package com.example.afinal.feature_task.presentation.tasks

import com.example.afinal.feature_task.domain.model.Task

data class TasksState(
    val tasks: List<Task> = emptyList(),
    val groupByMY: Map<String, List<Task>> = emptyMap()
)