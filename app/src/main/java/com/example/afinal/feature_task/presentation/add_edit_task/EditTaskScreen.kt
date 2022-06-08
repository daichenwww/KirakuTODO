package com.example.afinal.feature_task.presentation.add_edit_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.presentation.add_edit_task.components_and_utils.AddEditCommon
import com.example.afinal.feature_task.presentation.util.Screen
import com.example.afinal.feature_task.presentation.add_edit_task.components_and_utils.TransparentHintTextField
import com.example.afinal.feature_task.presentation.add_edit_task.components_and_utils.light_color_map
import com.example.afinal.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditTaskScreen(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    AddEditCommon(navController, viewModel,isAddPage = false)
}