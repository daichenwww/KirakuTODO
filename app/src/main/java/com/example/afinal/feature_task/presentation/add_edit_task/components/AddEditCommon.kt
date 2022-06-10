package com.example.afinal.feature_task.presentation.add_edit_task.components

import android.app.DatePickerDialog
import android.widget.DatePicker
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
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
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
import com.example.afinal.feature_task.domain.model.Task
import com.example.afinal.feature_task.presentation.add_edit_task.AddEditTaskEvent
import com.example.afinal.feature_task.presentation.add_edit_task.AddEditTaskViewModel
import com.example.afinal.common.util.Screen
import com.example.afinal.common.util.fillZero
import com.example.afinal.common.util.mapToLightColor
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
    val planDateState = viewModel.taskPlanDate.value
    val autoPlan = remember { mutableStateOf(viewModel.taskPlan.value) }
    val sliderPos = remember { mutableStateOf(viewModel.taskEsTime.value.toFloat()) }

    val taskId = viewModel.currentTaskId

    val dDate = remember {mutableStateOf(dueDateState)}
    val pDate = remember {mutableStateOf(planDateState)}

    val mContext = LocalContext.current

    // for dueDatePicker
    val dYear: Int
    val dMonth: Int
    val dDay: Int

    val dCalendar = Calendar.getInstance()

    if(isAddPage){
        dYear = dCalendar.get(Calendar.YEAR)
        dMonth = dCalendar.get(Calendar.MONTH)
        dDay = dCalendar.get(Calendar.DAY_OF_MONTH)
    }
    else {
        dDate.value = dueDateState //dateStr
        val dayInt: List<Int> = dueDateState.split("-").toList().map { it.toInt() }
        dYear = dayInt[0]
        dMonth = dayInt[1]-1
        dDay = dayInt[2]
    }

    dCalendar.time = Date()

    val dDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, dYear: Int, dMonth: Int, dDayOfMonth: Int ->
            dDate.value = "$dYear-${dMonth+1}-$dDayOfMonth"
        }, dYear, dMonth, dDay
    )

    // for planDatePicker
    val pYear: Int
    val pMonth: Int
    val pDay: Int

    val pCalendar = Calendar.getInstance()

    if(isAddPage){
        pYear = pCalendar.get(Calendar.YEAR)
        pMonth = pCalendar.get(Calendar.MONTH)
        pDay = pCalendar.get(Calendar.DAY_OF_MONTH)
    }
    else {
        pDate.value = planDateState
        val dayInt: List<Int> = planDateState.split("-").toList().map { it.toInt() }
        pYear = dayInt[0]
        pMonth = dayInt[1]-1
        pDay = dayInt[2]
    }

    pCalendar.time = Date()

    val pDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, pYear: Int, pMonth: Int, pDayOfMonth: Int ->
            pDate.value = "$pYear-${pMonth+1}-$pDayOfMonth"
        }, pYear, pMonth, pDay
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
                        viewModel.onEvent(AddEditTaskEvent.ChangeDueDate(fillZero(dDate.value)))
                        viewModel.onEvent(AddEditTaskEvent.ChangePlanDate(fillZero(pDate.value)))
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
            /*Text(
                text = "autoPlan = " + autoPlan.toString() + ", sliderPos = " + sliderPos.toString(),
                color = MaterialTheme.colors.onSecondary,
                style = MaterialTheme.typography.body1
            )*/
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
                        text = fillZero(dDate.value),
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
                            .background(((if (viewModel.taskColor.value == colorInt) { color }
                                  else { mapToLightColor[color] })!!))
                            .clickable { viewModel.onEvent(AddEditTaskEvent.ChangeColor(colorInt)) }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth().size(80.dp)
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
                    autoPlan.value = !(autoPlan.value)
                    viewModel.onEvent(AddEditTaskEvent.ChangeAutoPlan(autoPlan.value))
                }) {
                    Image(
                        painter =   if (autoPlan.value) painterResource(id = R.drawable.swon)
                                    else painterResource(id = R.drawable.swoff),
                        contentDescription = null,
                        modifier = Modifier.size(65.dp)
                    )
                }
            }
            if (autoPlan.value){
                Row(
                    modifier = Modifier
                        .fillMaxWidth().size(80.dp)
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
                            text = sliderPos.value.toInt().toString() + "hr",
                            color = MaterialTheme.colors.onSecondary,
                            style = MaterialTheme.typography.body1
                        )
                        Slider(
                            value = sliderPos.value,
                            onValueChange = {
                                sliderPos.value = it
                                viewModel.onEvent(AddEditTaskEvent.ChangeEsTime((sliderPos.value).toInt())) },
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
                        .fillMaxWidth().size(80.dp)
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
                        modifier = Modifier.size(250.dp, 65.dp).padding(start = 40.dp,top = 7.dp),
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
                            text = fillZero(pDate.value),
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