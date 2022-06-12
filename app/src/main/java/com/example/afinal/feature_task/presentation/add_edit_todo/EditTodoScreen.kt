package com.example.afinal.feature_task.presentation.add_edit_todo

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.feature_task.presentation.add_edit_todo.components.AddEditCommon

@ExperimentalAnimationApi
@Composable
fun EditTodoScreen(
    navController: NavController,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {
    AddEditCommon(navController, viewModel,isAddPage = false)
}