package com.example.afinal

import AccumulationPage
import HelpPage
import SettingPage
import StampsBookPageP1
import StampsBookPageP2
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.afinal.ui.theme.FinalTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.afinal.feature_task.presentation.add_edit_task.AddTaskScreen
import com.example.afinal.feature_task.presentation.add_edit_task.EditTaskScreen
import com.example.afinal.feature_task.presentation.tasks.TasksScreen
import com.example.afinal.common.util.Screen
import com.example.afinal.feature_task.presentation.stampbook.StampsBookPageP3
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalTheme {
                Surface(color = MaterialTheme.colors.background){
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TasksScreen.route
                    ){
                        composable(route = Screen.TasksScreen.route) { TasksScreen(navController = navController) }
                        composable(route = Screen.AddTaskScreen.route) { AddTaskScreen(navController = navController) }
                        composable(
                            route = Screen.EditTaskScreen.route +
                                    "?taskId={taskId}",
                            arguments = listOf(
                                navArgument(
                                    name = "taskId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) { EditTaskScreen(navController = navController) }
//                        composable("addtask")  { AddTaskPage(navController = navController)}
                        composable("setting")  { SettingPage(navController = navController)}
                        composable("accumulation")  { AccumulationPage(navController = navController)}
                        composable("stampsbookp1")  { StampsBookPageP1(navController = navController)}
                        composable("stampsbookp2")  { StampsBookPageP2(navController = navController)}
                        composable("stampsbookp3")  { StampsBookPageP3(navController = navController)}
                        composable("help")  { HelpPage(navController = navController)}
                    }

                }
            }
        }
    }
}