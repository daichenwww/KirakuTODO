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
import com.example.afinal.feature_task.presentation.util.Screen
import com.example.afinal.feature_task.presentation.add_edit_task.components.TransparentHintTextField
import com.example.afinal.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditTaskScreen(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    val titleState = viewModel.taskTitle.value
    val dueDateState = viewModel.taskDueDate.value

    val scaffoldState = rememberScaffoldState()

    // val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditTaskViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditTaskViewModel.UiEvent.SaveTask -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Scaffold(
        topBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth().size(80.dp),
            ) {
                TextButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    onClick = { navController.navigate(Screen.TasksScreen.route) },
                    modifier = Modifier
                        .size(100.dp, 60.dp)
                        .absoluteOffset(x = (-5).dp)
                ) {
                    Text(
                        text = "取消",
                        color = MaterialTheme.colors.background,
                        style = MaterialTheme.typography.h3
                    )
                }
                Text( /*pass parameter to decide edit or add*/
                    text = "新增任務", // else "編輯任務", //TODO: 要可以變成編輯
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.size(210.dp, 45.dp)
                )
                TextButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    onClick = { viewModel.onEvent(AddEditTaskEvent.SaveTask) },
                    modifier = Modifier
                        .size(100.dp, 60.dp)
                        .absoluteOffset(x = (5).dp)
                ) {
                    Text(
                        text = "完成",
                        color = MaterialTheme.colors.background,
                        style = MaterialTheme.typography.h3
                    )
                }
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().size(80.dp)
                    .padding(top = 20.dp, start = 20.dp, end = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "標題",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.body1
                )
                TransparentHintTextField(
                    modifier = Modifier.size(250.dp, 65.dp),
                    text = titleState.text,
                    hint = titleState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditTaskEvent.EnteredTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditTaskEvent.ChangeTitleFocus(it))
                    },
                    isHintVisible = titleState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.body1
                )
            }
            // Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth().size(80.dp)
                    .padding(start = 20.dp, end = 60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "到期日",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.body1
                )
                TransparentHintTextField(
                    modifier = Modifier.size(250.dp, 65.dp),
                    text = dueDateState.text,
                    hint = dueDateState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditTaskEvent.EnteredDueDate(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditTaskEvent.ChangeDueDateFocus(it))
                    },
                    isHintVisible = dueDateState.isHintVisible,
                    textStyle = MaterialTheme.typography.body1
                )
            }

            // template color row--------------
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth().size(80.dp)
                    .padding(start = 30.dp, end = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Task.taskColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(
                                ((if (viewModel.taskColor.value == colorInt) { color } else { dark_color_map[color] })!!)
                            )
                            .clickable {
                                viewModel.onEvent(AddEditTaskEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
        }
    }
}

val dark_color_map  = mapOf(RedTag to LightRedTag, YellowTag to LightYellowTag, GreenTag to LightGreenTag,
                                BlueTag to LightBlueTag, PurpleTag to LightPurpleTag)