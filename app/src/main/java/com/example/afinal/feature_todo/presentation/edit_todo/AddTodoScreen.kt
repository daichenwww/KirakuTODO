package com.example.afinal.feature_todo.presentation.edit_todo

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.feature_task.presentation.add_edit_task.AddEditTaskViewModel
import com.example.afinal.feature_task.presentation.add_edit_task.components.AddEditCommon

@Composable
fun AddTodoScreen(
    navController: NavController,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {
    AddEditCommon(navController, viewModel, isAddPage = true)
}