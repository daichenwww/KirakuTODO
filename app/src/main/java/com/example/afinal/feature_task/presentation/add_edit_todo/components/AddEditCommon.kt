package com.example.afinal.feature_task.presentation.add_edit_todo.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.R
import com.example.afinal.common.util.*
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.domain.model.Task.Companion.taskColors
import com.example.afinal.feature_task.domain.model.Todo
import com.example.afinal.feature_task.presentation.add_edit_task.components.TransparentHintTextField
import com.example.afinal.feature_task.presentation.add_edit_todo.AddEditTodoEvent
import com.example.afinal.feature_task.presentation.add_edit_todo.AddEditTodoViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.flow.collectLatest
import java.util.*


@ExperimentalAnimationApi
@Composable

fun AddEditCommon(
    navController: NavController,
    viewModel: AddEditTodoViewModel = hiltViewModel(),
    isAddPage: Boolean
) {
    val titleState = viewModel.todoTitle.value
    val dueDateState = viewModel.todoDueDate.value
    val planDateState = viewModel.todoPlanDate.value

    val todoId = viewModel.currentTodoId

    // for date picker
    val dDate = remember {mutableStateOf(dueDateState)}
    val pDate = remember {mutableStateOf(planDateState)}

    val mContext = LocalContext.current

    val dDateInt: List<Int> = getDateInt(viewModel.todoDueDate.value)

    // When it's AddPage, the DueDate now is current date. Else it's DueDate of the task.
    val dYear = dDateInt[0]
    val dMonth = dDateInt[1]-1
    val dDay = dDateInt[2]

    val dDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, dYear: Int, dMonth: Int, dDayOfMonth: Int ->
            dDate.value = "$dYear-${dMonth+1}-$dDayOfMonth"
            viewModel.onEvent(AddEditTodoEvent.ChangeDueDate(fillZero(dDate.value)))
        }, dYear, dMonth, dDay
    )

    val pDateInt: List<Int> = getDateInt(viewModel.todoPlanDate.value)

    val pYear = pDateInt[0]
    val pMonth = pDateInt[1]-1
    val pDay = pDateInt[2]

    val pDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, pYear: Int, pMonth: Int, pDayOfMonth: Int ->
            pDate.value = "$pYear-${pMonth+1}-$pDayOfMonth"
            viewModel.onEvent(AddEditTodoEvent.ChangePlanDate(fillZero(pDate.value)))
        }, pYear, pMonth, pDay
    )

    val scaffoldState = rememberScaffoldState()

    var buttonScene by remember {mutableStateOf("on")}

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditTodoViewModel.UiEvent.SaveTodo -> {
                    navController.navigateUp()
                }
                is AddEditTodoViewModel.UiEvent.ShowSnackbar -> {
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
                    onClick = { navController.navigate(Screen.TodosScreen.route) },
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
                    text = if(isAddPage) "新增Todo" else "編輯Todo",
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.size(210.dp, 45.dp)
                )
                TextButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    onClick = {
                        /*viewModel.onEvent(AddEditTaskEvent.ChangeDueDate(fillZero(dDate.value)))
                        viewModel.onEvent(AddEditTaskEvent.ChangePlanDate(fillZero(pDate.value)))*/
                        viewModel.onEvent(AddEditTodoEvent.SaveTodo) },
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
                    onValueChange = { viewModel.onEvent(AddEditTodoEvent.EnteredTitle(it)) },
                    onFocusChange = { viewModel.onEvent(AddEditTodoEvent.ChangeTitleFocus(it)) },
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
                    modifier = Modifier
                        .size(250.dp, 65.dp)
                        .padding(start = 40.dp, top = 7.dp),
                    onClick = { dDatePickerDialog.show() },
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
                        text = viewModel.todoDueDate.value,
                        modifier = Modifier.size(200.dp),
                        textAlign = TextAlign.Left,
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = Modifier.width(50.dp))
            }
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
                            .background(
                                ((if (viewModel.todoColor.value == colorInt) {
                                    color
                                } else {
                                    mapToLightColor[color]
                                })!!)
                            )
                            .clickable { viewModel.onEvent(AddEditTodoEvent.ChangeColor(colorInt)) }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp)
                    .padding(start = 25.dp, end = 50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "自動排程",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.body1
                )
                IconButton(onClick = {
                    buttonScene = when (buttonScene) {
                        "on" -> "off"
                        else -> "on"
                    }
                    viewModel.onEvent(AddEditTodoEvent.ChangeAutoPlan(!viewModel.todoPlan.value))
                }) {
                    Crossfade(
                        targetState = buttonScene,
                        animationSpec = tween(durationMillis = 500)) { buttonScene->
                        when (buttonScene) {
                            "on" -> Image (
                                painter = painterResource(id = R.drawable.swon),
                                contentDescription = null,
                                modifier = Modifier.size(65.dp)
                            )
                            else -> Image(
                                    painter =  painterResource(id = R.drawable.swoff),
                                    contentDescription = null,
                                    modifier = Modifier.size(65.dp)
                                )
                        }
                    }
                }
            }
            if (viewModel.todoPlan.value){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(80.dp)
                        .padding(start = 25.dp, end = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "預計費時",
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body1
                    )
                    Column(modifier = Modifier.padding(start = 50.dp, end = 10.dp, top = 20.dp))
                    {
                        Text(
                            text = viewModel.todoEsTime.value.toString() + "hr",
                            color = MaterialTheme.colors.onSecondary,
                            style = MaterialTheme.typography.body1
                        )
                        Slider(
                            value = viewModel.todoEsTime.value.toFloat(),
                            onValueChange = { pos ->
                                viewModel.onEvent(AddEditTodoEvent.ChangeEsTime((pos).toInt())) },
                            steps = 7,
                            valueRange = 0f..8f,
                            colors = SliderDefaults.colors(
                                thumbColor = MaterialTheme.colors.onBackground,
                                inactiveTrackColor = DarkGray,
                                activeTrackColor = MaterialTheme.colors.primary,
                                inactiveTickColor = White,
                                activeTickColor = MaterialTheme.colors.onPrimary
                            )
                        )
                    }
                }
            }
            else{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(80.dp)
                        .padding(start = 20.dp, end = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "安排日",
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body1
                    )
                    TextButton(
                        modifier = Modifier
                            .size(250.dp, 65.dp)
                            .padding(start = 40.dp, top = 7.dp),
                        onClick = { pDatePickerDialog.show() },
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
                            text = viewModel.todoPlanDate.value,
                            modifier = Modifier.size(200.dp),
                            textAlign = TextAlign.Left,
                            color = MaterialTheme.colors.onSecondary,
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Spacer(modifier = Modifier.width(50.dp))
                }
            }

            if(!isAddPage) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextButton(
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onBackground),
                        onClick ={
                            viewModel.onEvent(AddEditTodoEvent.DeleteTodo(todoId))
                            navController.navigate(Screen.TodosScreen.route)
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