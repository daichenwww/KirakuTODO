package com.example.afinal.feature_todo.presentation.todos.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.example.afinal.common.util.mapToGraph
import com.example.afinal.feature_todo.domain.model.Todo

@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(start = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = mapToGraph[Color(todo.todoColor)]!!),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        if(todo.todoDone) {
            Text(
                text = todo.todoTitle,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary,
                textDecoration =  TextDecoration.LineThrough,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        else {
            Text(
                text = todo.todoTitle,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        if(todo.todoAutoPlan) {
            Text(
                text = todo.todoTime.toString() + " hr",
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