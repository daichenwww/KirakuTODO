// edited by: zshzzz
package com.example.afinal.feature_task.domain.use_case

import com.example.afinal.feature_task.domain.repository.TaskRepository

class GetDoneTaskNumberInRange(private val repository: TaskRepository) {
    suspend operator fun invoke(start: String, end: String): Int {
        val num = repository.getDoneTaskNumberInRange(start, end)
        if(num.toFloat().isNaN()) return 0
        return repository.getDoneTaskNumberInRange(start, end)
    }
}