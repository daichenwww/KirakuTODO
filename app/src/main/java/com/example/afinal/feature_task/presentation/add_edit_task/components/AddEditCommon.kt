package com.example.afinal.feature_task.presentation.add_edit_task.components

import android.app.DatePickerDialog
import android.widget.DatePicker
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.presentation.add_edit_task.AddEditTaskEvent
import com.example.afinal.feature_task.presentation.add_edit_task.AddEditTaskViewModel
import com.example.afinal.feature_task.presentation.common.util.Screen
import com.example.afinal.feature_task.presentation.common.util.fillZero
import com.example.afinal.feature_task.presentation.common.util.mapToLightColor
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@Composable

fun AddEditCommon(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel(),
    isAddPage: Boolean
) {
    val titleState = viewModel.taskTitle.value
    val dueDateState = viewModel.taskDueDate.value
    val mDate = remember {mutableStateOf(dueDateState)}
    val taskId = viewModel.currentTaskId

    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mYear-${mMonth+1}-$mDayOfMonth"
        }, mYear, mMonth, mDay
    )

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditTaskViewModel.UiEvent.SaveTask -> {
                    navController.navigateUp()
                }
                is AddEditTaskViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }
    Scaffold(
        topBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp),
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
                Text(
                    text = if(isAddPage) "新增任務" else "編輯任務",
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.size(210.dp, 45.dp)
                )
                TextButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    onClick = {
                        viewModel.onEvent(AddEditTaskEvent.ChangeDueDate(fillZero(mDate.value)))
                        viewModel.onEvent(AddEditTaskEvent.SaveTask) },
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
        Column{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp)
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
                    onValueChange = { viewModel.onEvent(AddEditTaskEvent.EnteredTitle(it)) },
                    onFocusChange = { viewModel.onEvent(AddEditTaskEvent.ChangeTitleFocus(it)) },
                    isHintVisible = titleState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.body1
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp)
                    .padding(start = 20.dp, end = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "到期日",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.body1
                )

                TextButton(
                    modifier = Modifier.size(250.dp, 65.dp).padding(start = 40.dp,top = 7.dp),
                    onClick = { mDatePickerDialog.show() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.background,
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp
                    ),
                ){
                    Text(
                        text = fillZero(mDate.value),
                        modifier = Modifier.size(200.dp),
                        textAlign = TextAlign.Left,
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = Modifier.width(50.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp)
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
                            .background(((if (viewModel.taskColor.value == colorInt) { color }
                                  else { mapToLightColor[color] })!!))
                            .clickable { viewModel.onEvent(AddEditTaskEvent.ChangeColor(colorInt)) }
                    )
                }
            }
            if(!isAddPage) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { TextButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onBackground),
                    onClick ={
                        viewModel.onEvent(AddEditTaskEvent.DeleteTask(taskId))
                        navController.navigate(Screen.TasksScreen.route)
                    },
                    modifier = Modifier.size(100.dp, 60.dp)
                ) {
                    Text(
                        text = "刪除",
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.h3
                    )
                }
                }
            }
        }
    }
}