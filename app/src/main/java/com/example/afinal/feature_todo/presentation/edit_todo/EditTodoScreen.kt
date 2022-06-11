package com.example.afinal.feature_todo.presentation.edit_todo

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.feature_todo.presentation.edit_todo.AddEditCommon

@Composable
fun EditTodoScreen(
    navController: NavController,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {
    AddEditCommon(
        navController, viewModel, isAddPage = false
    )
}