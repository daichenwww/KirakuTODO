package com.example.afinal.feature_task.presentation.todos.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.afinal.R
import com.example.afinal.common.util.mapToGraph
import com.example.afinal.feature_task.domain.model.Todo
import com.example.afinal.feature_task.presentation.todos.TodosEvent
import com.example.afinal.feature_task.presentation.todos.TodosViewModel
import com.example.afinal.ui.theme.BrownBackgroundDark
import com.example.afinal.ui.theme.BrownBackgroundLight
import com.example.afinal.ui.theme.BrownWordLight
import com.example.afinal.ui.theme.doneGray
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier ,
    viewModel: TodosViewModel = hiltViewModel()
) {
    val letDone = SwipeAction(
        onSwipe = {
            viewModel.onEvent(TodosEvent.DoneTodo(todo.id))
        },
        icon = {
            Icon(
                modifier = Modifier.padding(5.dp)
                    .background(BrownBackgroundLight),
                painter = painterResource(id = R.drawable.ic_baseline_done_24),
                contentDescription = null,
                tint = BrownWordLight
            )
        }
        , background = BrownBackgroundDark,
    )
    val cancelDone = SwipeAction(
        onSwipe = {
            viewModel.onEvent(TodosEvent.CancelTodo(todo.id))
        },
        icon = {
            Icon(
                modifier = Modifier.padding(5.dp)
                    .background(BrownBackgroundLight),
                painter = painterResource(id = R.drawable.ic_baseline_remove_done_24),
                contentDescription = null,
                tint = BrownWordLight
            )
        },
        background = BrownBackgroundDark,
    )
    SwipeableActionsBox(
        startActions = listOf(cancelDone),
        endActions = listOf(letDone),
        modifier = modifier.padding(start = 5.dp)
    ) {
        val rowColor: Color = if (todo.done) doneGray else BrownBackgroundLight
        Row(
            modifier = modifier
                .padding(start = 0.dp)
                .background(rowColor),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = mapToGraph[Color(todo.color)]!!),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))

            if(todo.done) {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                    textDecoration =  TextDecoration.LineThrough,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            else {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if(todo.autoPlan) {
                Text(
                    text = todo.esTimeCost.toString() + " hr",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 80.dp),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Right,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}