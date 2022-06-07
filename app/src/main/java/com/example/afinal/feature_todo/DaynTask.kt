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
import androidx.compose.ui.unit.dp
import com.example.afinal.R


// component For TODO screen

@Composable
fun DateAndTask(/*date: Int, day: String,
                numberOfTasks: Int, tasks: com.example.afinal.TasksList,
                numberOfStamps: Int, stamps: com.example.afinal.StampsList
                ... an example to use*/){
    Column(){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    text = "28",
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = "Mon",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.absoluteOffset(0.dp, (-10).dp)
                )
            }
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
        Row(
            modifier = Modifier
                .padding(start = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.tag_blue),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "TODO XXXXX",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary
            )
            Text(
                text = "1hr",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 80.dp),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.Right
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.tag_yellow),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "TODO XXXXX",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary
            )
            Text(
                text = "1hr",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 80.dp),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.Right
            )
        }

    }
}