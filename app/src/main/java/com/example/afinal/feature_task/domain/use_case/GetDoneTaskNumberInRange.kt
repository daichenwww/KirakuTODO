// edited by: zshzzz
package com.example.afinal.feature_task.domain.use_case

import com.example.afinal.feature_task.domain.repository.TaskRepository

class GetDoneTaskNumberInRange(private val repository: TaskRepository) {
    operator fun invoke(start: String, end: String): Int {
        return repository.getDoneTaskNumberInRange(start, end)
    }
}