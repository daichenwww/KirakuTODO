package com.example.afinal.feature_task.domain.use_case.todo

import com.example.afinal.feature_task.domain.model.Todo
import com.example.afinal.feature_task.domain.repository.TodoRepository

//class GetTodoByDate (private val repository: TodoRepository) {
//    suspend operator fun invoke(date: String): List<Todo>? {
//        return repository.getTodoByDate(date)
//    }
//}

class GetTimeByDate (private val repository: TodoRepository) {
    suspend operator fun invoke(date: String): Long{
        val todos = repository.getTodoByDate(date)
        var usedTime: Long = 0
        if (todos != null){
            for  (item in todos) {
                usedTime += item.esTimeCost
            }
        }
        return usedTime
    }
}