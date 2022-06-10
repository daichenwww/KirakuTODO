package com.example.afinal.feature_task.presentation.tasks.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.afinal.R


@Composable
fun SideBar(navController: NavController) { // 這個drawer的大小好像只能改形狀沒辦法改他點掉要推出的範圍
    Column( modifier = Modifier
        .fillMaxHeight()
        .size(150.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top){
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .size(150.dp, 80.dp),
            contentAlignment = Alignment.CenterEnd
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .absoluteOffset(x = (-15).dp)
            )
        }
        Button(
            modifier = Modifier.size(150.dp, 60.dp),
            onClick = {navController.navigate("setting")},
            contentPadding = PaddingValues(start = 7.dp, end = 7.dp),
            elevation = null,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_setting),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 7.dp, end = 7.dp)
            )
            Text(
                text="設定",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.padding(start = 7.dp, end = 7.dp)
            ) }
        Button(
            modifier = Modifier.size(150.dp, 60.dp),
            onClick = {navController.navigate("accumulation")},
            contentPadding = PaddingValues(start = 7.dp, end = 7.dp),
            elevation = null,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_weeks),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 7.dp, end = 7.dp)
            )
            Text(
                text="累計",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.padding(start = 7.dp, end = 7.dp)
            ) }
        Button(
            modifier = Modifier.size(150.dp, 60.dp),
            onClick = {navController.navigate("stampsbook")},
            contentPadding = PaddingValues(start = 7.dp, end = 7.dp),
            elevation = null,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_book),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 7.dp, end = 7.dp)
            )
            Text(
                text="圖鑑",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.padding(start = 7.dp, end = 7.dp)
            ) }
        Button(
            modifier = Modifier.size(150.dp, 60.dp),
            onClick = {navController.navigate("help")},
            contentPadding = PaddingValues(start = 7.dp, end = 7.dp),
            elevation = null,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_help),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(start = 7.dp, end = 7.dp)
            )
            Text(
                text="說明",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.padding(start = 7.dp, end = 7.dp)
            ) }
    }
}