package com.example.afinal.feature_task.presentation.todos.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp

import com.example.afinal.R
import com.example.afinal.feature_task.domain.model.Todo

@Composable
fun StampItem (stamp: Int, done:Boolean
){
    if(done) {
        when (stamp) {
            0 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_000),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            1 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_001),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            2 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_002),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            3 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_003),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            4 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_004),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            5 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_005),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            6 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_006),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            7 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_007),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            8 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_008),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            9 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_009),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            10 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_010),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            11 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_011),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            12 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_012),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            13 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_013),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            14 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_014),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            15 -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_015),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
            else -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_undefined),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp, 45.dp)
                )
            }
        }
    }
}