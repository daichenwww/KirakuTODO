package com.example.afinal.feature_todo.presentation.todos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.R
import com.example.afinal.feature_todo.presentation.todos.component.ListwithHeader
import com.example.afinal.feature_task.presentation.tasks.component.SideBar
import com.example.afinal.common.util.Screen
import kotlinx.coroutines.launch

@Composable
fun TodosScreen(
    navController: NavController,
    viewModel: TodosViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp),
                navigationIcon = {
                    IconButton(
                        onClick = { scope.launch { scaffoldState.drawerState.open() } }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .absoluteOffset(x = 5.dp)
                        )
                    }
                },
                title = {
                    Text(text= "June 2022",
                        style = MaterialTheme.typography.h2, //TODO: Update Title
                        modifier = Modifier.absoluteOffset(x = (-10).dp))
                },
                actions = {
                    IconButton(
                        { navController.navigate(Screen.TasksScreen.route) }
                        // TODO: navigate to TODO screen
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_modedeadline),
                            contentDescription = null,
                            modifier = Modifier
                                .size(45.dp)
                                .absoluteOffset(x = (-10).dp)
                        )
                        //for deadline image: Image(ic_modedeadline, size(45.dp)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.onPrimary,
                contentColor = MaterialTheme.colors.background,
                modifier = Modifier.size(100.dp),
                onClick = { navController.navigate(Screen.AddTodoScreen.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        drawerContent = { SideBar(navController) },
        drawerContentColor = MaterialTheme.colors.onSecondary,
        drawerShape = sideBarShape(),
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerGesturesEnabled = true,
        drawerScrimColor = Color(0x33000000), //半透明灰色
    ) {
        ListwithHeader(state, navController)
    }
}

private fun sideBarShape() =  object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(Rect(0f,0f,500f /* width */, 3000f /* height */))
    }
}