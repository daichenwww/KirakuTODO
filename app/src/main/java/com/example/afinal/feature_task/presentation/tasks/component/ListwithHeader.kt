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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.afinal.R
import com.example.afinal.feature_task.presentation.tasks.TasksState
import com.example.afinal.common.util.Screen
import com.example.afinal.common.util.getDateName
import com.example.afinal.common.util.getDay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListwithHeader(
    state: TasksState,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().absoluteOffset(x = (-4).dp, y = (-15).dp),
        contentPadding = PaddingValues(15.dp),
    ){
        // TODO: add stamp
        state.grouped.forEach { (section, sectionTasks) ->
            stickyHeader {
                Card(backgroundColor = MaterialTheme.colors.background, elevation = 0.dp)
                {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            modifier = Modifier.padding(top = 10.dp),
                            horizontalAlignment =  Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = getDay(section),
                                color = MaterialTheme.colors.onPrimary,
                                style = MaterialTheme.typography.h1
                            )
                            Text(
                                text = getDateName(section),
                                color = MaterialTheme.colors.primary,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.absoluteOffset(0.dp, (-10).dp)
                            )
                        } //TODO: update stamp
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.stamp_000),
                            contentDescription = null,
                            modifier = Modifier.size(60.dp, 45.dp)
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
                    task = task,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screen.EditTaskScreen.route + "?taskId=${task.id}"
                            )
                        }
                )
            }
        }
    }
}