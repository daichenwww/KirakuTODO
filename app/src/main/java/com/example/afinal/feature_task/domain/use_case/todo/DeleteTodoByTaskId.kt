package com.example.afinal.feature_task.domain.use_case.todo

import com.example.afinal.feature_task.domain.repository.TodoRepository

class DeleteTodoByTaskId (private val repository: TodoRepository) {
    suspend operator fun invoke(taskId: Int?) {
        if(taskId != null) repository.deleteTodoByTaskId(taskId)
    }
}