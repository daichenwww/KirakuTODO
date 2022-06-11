package com.example.afinal

import AccumulationPage
import HelpPage
import SettingPage
import StampsBookPage
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.afinal.ui.theme.FinalTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.afinal.feature_task.presentation.add_edit_task.AddTaskScreen
import com.example.afinal.feature_task.presentation.add_edit_task.EditTaskScreen
import com.example.afinal.feature_task.presentation.tasks.TasksScreen
import com.example.afinal.common.util.Screen
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


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
                        composable("accumulation")  { AccumulationPage(navController = navController, "1")}
                        composable("stampsbook")  { StampsBookPage(navController = navController, "1")}
                        composable("help")  { HelpPage(navController = navController)}
                    }

                }
            }
        }
        //owo
        val context = applicationContext
        setAlarm(context)//owo
    }
    //owo
    private fun setAlarm(context: Context) {
        //notice!! calendar must choose import java.util, not import android.icu
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 16)
            set(Calendar.MINUTE, 58)
        }
        val alarmMgr = context.getSystemService(ComponentActivity.ALARM_SERVICE) as AlarmManager
        //notice！！ don't choose android.app.Notification
        val intent = Intent(context, com.example.afinal.Notification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

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
//owo
/*
private fun setAlarm(context: Context) {
    val timeSec = System.currentTimeMillis() + 10000
    val alarmManager = context.getSystemService(ComponentActivity.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, Notification::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    alarmManager.set(AlarmManager.RTC_WAKEUP, timeSec, pendingIntent)
}*/

}

