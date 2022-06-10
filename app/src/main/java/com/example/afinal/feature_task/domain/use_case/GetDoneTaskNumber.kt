// edited by: zshzzz
package com.example.afinal.feature_task.domain.use_case

import com.example.afinal.feature_task.domain.repository.TaskRepository

class GetDoneTaskNumber (private val repository: TaskRepository){
    suspend operator fun invoke(): Int {
        return repository.getDoneTaskNumber()
    }
}