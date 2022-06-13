package com.example.afinal.feature_task.presentation.tasks


sealed class TasksEvent {
    data class DoneTask(val taskId: Int?): TasksEvent()
    data class CancelTask(val taskId: Int?): TasksEvent()
}