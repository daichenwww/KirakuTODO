package com.example.afinal

import AccumulationPage
import HelpPage
import SettingPage
import StampsBookPage
import StampsBookPageP1
import StampsBookPageP2
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
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
import com.example.afinal.feature_task.presentation.add_edit_todo.AddTodoScreen
import com.example.afinal.feature_task.presentation.tasks.TasksScreen
import com.example.afinal.common.util.Screen
import com.example.afinal.feature_task.presentation.add_edit_todo.EditTodoScreen
import com.example.afinal.feature_task.presentation.stampbook.StampsBookPageP3
import com.example.afinal.feature_task.presentation.todos.TodosScreen
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@ExperimentalAnimationApi
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
                        composable(route = Screen.TodosScreen.route) { TodosScreen(navController = navController) }
                        composable(route = Screen.AddTaskScreen.route) { AddTaskScreen(navController = navController) }
                        composable(route = Screen.AddTodoScreen.route) { AddTodoScreen(navController = navController) }
                        composable(
                            route = Screen.EditTaskScreen.route + "?taskId={taskId}",
                            arguments = listOf(
                                navArgument(name = "taskId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) { EditTaskScreen(navController = navController) }
                        composable(
                            route = Screen.EditTodoScreen.route + "?todoId={todoId}",
                            arguments = listOf(
                                navArgument(name = "todoId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) { EditTodoScreen(navController = navController) }
//                        composable("addtask")  { AddTaskPage(navController = navController)}
                        composable("setting")  { SettingPage(navController = navController)}
                        composable("accumulation")  { AccumulationPage(navController = navController)}
                        composable("help")  { HelpPage(navController = navController)}
                        composable("stampsbookp1")  { StampsBookPageP1(navController = navController)}
                        composable("stampsbookp2")  { StampsBookPageP2(navController = navController)}
                        composable("stampsbookp3")  { StampsBookPageP3(navController = navController) }
                    }

                }
            }
        }
        //owo
        val context = applicationContext
        setAlarm(context)//owo
    }
    private fun setAlarm(context: Context) {
        //notice!! calendar must choose import java.util, not import android.icu
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 14)
            set(Calendar.MINUTE, 7)
        }
        val alarmMgr = context.getSystemService(ComponentActivity.ALARM_SERVICE) as AlarmManager
        //notice！！ don't choose android.app.Notification
        val intent = Intent(context, Notification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_MUTABLE)

        /*alarmMgr.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )*/
        alarmMgr.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}
