package com.example.afinal.feature_task.domain.use_case.todo

import com.example.afinal.feature_task.domain.repository.TodoRepository

class GetDoneTodoNumber (private val repository: TodoRepository){
    suspend operator fun invoke(): Int {
        val num = repository.getDoneTodoNumber()
        if(num.toFloat().isNaN()) return 0
        return num
    }
}