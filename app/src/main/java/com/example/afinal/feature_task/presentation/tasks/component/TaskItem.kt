package com.example.afinal.feature_task.presentation.tasks.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.afinal.R
import com.example.afinal.feature_task.domain.model.Task

@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(start = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.tag_blue),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = task.title,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.secondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = task.esTimeCost,
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