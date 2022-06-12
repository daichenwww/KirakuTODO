package com.example.afinal.feature_task.presentation.add_edit_task

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.feature_task.presentation.add_edit_task.components.AddEditCommon

@ExperimentalAnimationApi
@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    AddEditCommon(navController, viewModel, isAddPage = true)
}