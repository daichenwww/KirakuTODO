package com.example.afinal.feature_task.presentation.common.util

sealed class Screen(val route: String) {
    object TasksScreen: Screen("tasks_screen")
    object AddTaskScreen: Screen("add_task_screen")
    object EditTaskScreen: Screen("edit_task_screen")
}