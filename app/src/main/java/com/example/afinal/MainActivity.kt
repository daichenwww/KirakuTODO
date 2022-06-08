package com.example.afinal

import AddTaskPage
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
//import com.example.afinal.feature_task.presentation.add_edit_task.AddEditTaskScreen
import com.example.afinal.feature_task.presentation.tasks.TasksScreen
import com.example.afinal.feature_task.presentation.util.Screen
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
//                         startDestination = "addtask"
                        startDestination = Screen.TasksScreen.route
                    ){
                        composable(route = Screen.TasksScreen.route) { TasksScreen(navController = navController) }
                        /*composable(
                            route = Screen.AddEditTaskScreen.route +
                                    "?taskId={taskId}",
                            arguments = listOf(
                                navArgument(
                                    name = "taskId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            AddEditTaskScreen(navController = navController)
                        }*/
//                        composable("mainpage") { MainPage(navController = navController)}
//                        composable("addtask")  { AddTaskPage(navController = navController)}
//                        composable("setting")  { SettingPage(navController = navController)}
//                        composable("accumulation")  { AccumulationPage(navController = navController, "1")}
//                        composable("stampsbook")  { StampsBookPage(navController = navController, "1")}
//                        composable("help")  { HelpPage(navController = navController)}
                    }

                }
            }
        }
    }
}