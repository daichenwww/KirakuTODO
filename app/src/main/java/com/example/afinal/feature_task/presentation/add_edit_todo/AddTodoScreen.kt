package com.example.afinal.feature_task.presentation.add_edit_todo

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.feature_task.presentation.add_edit_todo.components.AddEditCommon

//這裡實際上不會進來

@Composable
fun AddTodoScreen(
    navController: NavController,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {
    AddEditCommon(navController, viewModel, isAddPage = true)
}