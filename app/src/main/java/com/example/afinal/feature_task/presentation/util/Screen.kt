package com.example.afinal.feature_task.presentation.util

sealed class Screen(val route: String) {
    object TasksScreen: Screen("tasks_screen")
}