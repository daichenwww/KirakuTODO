package com.example.afinal.feature_task.presentation.add_edit_task

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.feature_task.presentation.add_edit_task.components.AddEditCommon

@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    AddEditCommon(navController, viewModel,isAddPage = true)
}