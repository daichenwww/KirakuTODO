package com.example.afinal.feature_task.domain.use_case

import com.example.afinal.feature_task.domain.model.InvalidTaskException
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.domain.repository.TaskRepository

class AddTask (private val repository: TaskRepository){
    @Throws(InvalidTaskException::class)
    suspend operator fun invoke(task: Task) {
        if(task.title.isBlank()) {
            throw InvalidTaskException("The title of the task can't be empty.")
        }
        repository.insertTask(task)
    }
}
