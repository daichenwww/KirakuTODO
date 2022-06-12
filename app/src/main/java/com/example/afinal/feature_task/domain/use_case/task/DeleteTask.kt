package com.example.afinal.feature_task.domain.use_case.task

import com.example.afinal.feature_task.domain.repository.TaskRepository

class DeleteTask(private val repository: TaskRepository) {
    suspend operator fun invoke(Id: Int?) {
        if(Id != null) repository.deleteTask(Id)
    }
}