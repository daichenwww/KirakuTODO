package com.example.afinal.feature_task.presentation.add_edit_task.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.R
import com.example.afinal.common.util.Screen
import com.example.afinal.common.util.fillZero
import com.example.afinal.common.util.getDateInt
import com.example.afinal.common.util.mapToLightColor
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.presentation.add_edit_task.AddEditTaskEvent
import com.example.afinal.feature_task.presentation.add_edit_task.AddEditTaskViewModel
import kotlinx.coroutines.flow.collectLatest

@ExperimentalAnimationApi
@Composable

fun AddEditCommon(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel(),
    isAddPage: Boolean
) {
    val titleState = viewModel.taskTitle.value
    val dueDateState = viewModel.taskDueDate.value
    val planDateState = viewModel.taskPlanDate.value

    val taskId = viewModel.currentTaskId

    // for date picker
    val dDate = remember {mutableStateOf(dueDateState)}
    val pDate = remember {mutableStateOf(planDateState)}

    val mContext = LocalContext.current

    val dDateInt: List<Int> = getDateInt(viewModel.taskDueDate.value)

    // When it's AddPage, the DueDate now is current date. Else it's DueDate of the task.
    val dYear = dDateInt[0]
    val dMonth = dDateInt[1]-1
    val dDay = dDateInt[2]

    val dDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, dYear: Int, dMonth: Int, dDayOfMonth: Int ->
            dDate.value = "$dYear-${dMonth+1}-$dDayOfMonth"
            viewModel.onEvent(AddEditTaskEvent.ChangeDueDate(fillZero(dDate.value)))
        }, dYear, dMonth, dDay
    )

    val pDateInt: List<Int> = getDateInt(viewModel.taskPlanDate.value)

    val pYear = pDateInt[0]
    val pMonth = pDateInt[1]-1
    val pDay = pDateInt[2]

    val pDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, pYear: Int, pMonth: Int, pDayOfMonth: Int ->
            pDate.value = "$pYear-${pMonth+1}-$pDayOfMonth"
            viewModel.onEvent(AddEditTaskEvent.ChangePlanDate(fillZero(pDate.value)))
        }, pYear, pMonth, pDay
    )

    val scaffoldState = rememberScaffoldState()

    var autoPlanScene by remember {mutableStateOf("on")}

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
                    onClick = { navController.navigateUp() },
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
                        /*viewModel.onEvent(AddEditTaskEvent.ChangeDueDate(fillZero(dDate.value)))
                        viewModel.onEvent(AddEditTaskEvent.ChangePlanDate(fillZero(pDate.value)))*/
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
                        text = viewModel.taskDueDate.value,
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
                                ((if (viewModel.taskColor.value == colorInt) {
                                    color
                                } else {
                                    mapToLightColor[color]
                                })!!)
                            )
                            .clickable { viewModel.onEvent(AddEditTaskEvent.ChangeColor(colorInt)) }
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
                    autoPlanScene = when (autoPlanScene) {
                        "on" -> "off"
                        else -> "on"
                    }
                    viewModel.onEvent(AddEditTaskEvent.ChangeAutoPlan(!viewModel.taskPlan.value))
                }) {
                    Crossfade(
                        targetState = autoPlanScene,
                        animationSpec = tween(durationMillis = 500)
                    ) { autoPlanScene->
                        when (autoPlanScene) {
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
            Crossfade(
                targetState = autoPlanScene,
                animationSpec = tween(durationMillis = 500)) { autoPlanScene->
                    when (autoPlanScene) {
                        "on"-> {
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
                                        text = viewModel.taskEsTime.value.toString() + "hr",
                                        color = MaterialTheme.colors.onSecondary,
                                        style = MaterialTheme.typography.body1
                                    )
                                    Slider(
                                        value = viewModel.taskEsTime.value.toFloat(),
                                        onValueChange = { pos ->
                                            viewModel.onEvent(AddEditTaskEvent.ChangeEsTime((pos).toInt())) },
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
                        else -> {
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
                                        text = viewModel.taskPlanDate.value,
                                        modifier = Modifier.size(200.dp),
                                        textAlign = TextAlign.Left,
                                        color = MaterialTheme.colors.onSecondary,
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                                Spacer(modifier = Modifier.width(50.dp))
                            }
                        }
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
                            viewModel.onEvent(AddEditTaskEvent.DeleteTask(taskId))
                            navController.navigateUp()
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
