// edited by: zshzzz
package com.example.afinal.feature_task.domain.use_case

import android.util.Log
import com.example.afinal.feature_task.domain.repository.TaskRepository

class GetDoneTaskNumber (private val repository: TaskRepository){
    suspend operator fun invoke(): Int {
        val num = repository.getDoneTaskNumber()
        if(num.toFloat().isNaN()) return 0
        return repository.getDoneTaskNumber()
    }
}