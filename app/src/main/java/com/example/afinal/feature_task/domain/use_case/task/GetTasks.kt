package com.example.afinal.feature_task.domain.use_case.task

import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTasks (private val repository: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> {
        return repository.getTasks().map { tasks ->
            tasks.sortedWith(compareBy({ it.dueDate }, { it.done }))}
    }
}