package com.example.afinal.feature_task.presentation.tasks.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.afinal.R
import com.example.afinal.feature_task.presentation.tasks.TasksState
import com.example.afinal.feature_task.presentation.util.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListwithHeader(
    state: TasksState,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().absoluteOffset(x = (-4).dp, y = (-15).dp),
        contentPadding = PaddingValues(15.dp)
    ){
        // TODO: add date and stamp
        val grouped = state.tasks.groupBy { it.dueDate }
        grouped.forEach { (section, sectionTasks) ->
            stickyHeader {
                Card(backgroundColor = MaterialTheme.colors.background, elevation = 0.dp)
                {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.padding(top = 10.dp)) {
                            Text(
                                text = section,
                                color = MaterialTheme.colors.onPrimary,
                                style = MaterialTheme.typography.h1
                            )
                            Text(
                                text = "Mon",
                                color = MaterialTheme.colors.primary,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.absoluteOffset(0.dp, (-10).dp)
                            )
                        } //TODO: update month, stamp
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.s_000),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.s_001),
                            contentDescription = null
                        )
                    }
                }
            }
            items(items = sectionTasks) { task ->
                TaskItem(
                    task = task,  //stickHeader?
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screen.AddEditTaskScreen.route + "?taskId=${task.id}"
                            )
                        }
                )
            }
        }
    }
}