package com.example.afinal.feature_task.domain.use_case.todo

import com.example.afinal.feature_task.domain.repository.TodoRepository


class GetDoneTodoNumberInRange(private val repository: TodoRepository) {
    suspend operator fun invoke(start: String, end: String): Int {
        val num = repository.getDoneTodoNumberInRange(start, end)
        if(num.toFloat().isNaN()) return 0
        return num
    }
}