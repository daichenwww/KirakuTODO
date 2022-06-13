package com.example.afinal.feature_task.presentation.todos

sealed class TodosEvent {
    data class DoneTodo(val todoId: Int?): TodosEvent()
    data class CancelTodo(val todoId: Int?): TodosEvent()
}