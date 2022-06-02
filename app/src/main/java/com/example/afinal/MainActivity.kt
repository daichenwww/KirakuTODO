package com.example.afinal

import AccumulationPage
import AddTaskPage
import HelpPage
import SettingPage
import StampsBookPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.afinal.ui.theme.FinalTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.afinal.R.drawable



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "mainpage"
                ){
                    composable("mainpage") { MainPage(navController = navController)}
                    composable("addtask")  { AddTaskPage(navController = navController)}
                    composable("setting")  { SettingPage(navController = navController)}
                    composable("accumulation")  { AccumulationPage(navController = navController, "1")}
                    composable("stampsbook")  { StampsBookPage(navController = navController, "1")}
                    composable("help")  { HelpPage(navController = navController)}
                }

            }
        }
    }
}


fun sideBarShape() =  object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(Rect(0f,0f,500f /* width */, 3000f /* height */))
    }
}

@Composable
fun MainPage(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
//    var selectIndex by remember { mutableStateOf(0) }
    var modeState by remember { mutableStateOf(true)}
    //val navTextList = listOf("主頁", "發現", "我的")
    //val iconList = listOf(Icons.Default.Home, Icons.Default.Favorite, Icons.Default.AccountBox)
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
                            painter = painterResource(id = drawable.ic_menu),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .absoluteOffset(x = 5.dp)
                        )
                    }
                },
                title = {
                    Text(text= " May 2022", style = MaterialTheme.typography.h2,
                    modifier = Modifier.absoluteOffset(x = (-10).dp))
                },
                actions = {
                    IconButton(
                        onClick = {modeState = !modeState }
                    ) {
                        if (modeState) {
                            Image(
                                painter = painterResource(id = drawable.ic_modetodo),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .absoluteOffset(x = (-10).dp)
                        ) }
                        else {Image(
                            painter = painterResource(id = drawable.ic_modedeadline),
                            contentDescription = null,
                            modifier = Modifier
                                .size(45.dp)
                                .absoluteOffset(x = (-10).dp)
                        ) }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.onPrimary,
                contentColor = MaterialTheme.colors.background,
                modifier = Modifier.size(100.dp),
                onClick = { /*TODO*/
                    navController.navigate("addtask")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        drawerContent = {
            SideBar(navController)
        },
        drawerContentColor = MaterialTheme.colors.onSecondary,
        drawerShape = sideBarShape(),
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerGesturesEnabled = true,
        drawerScrimColor = Color(0x33000000), //半透明灰色


    ) { // main content
        DatesAndTasks(modeState)
    }
}

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
                painter = painterResource(id = drawable.ic_menu),
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
                painter = painterResource(id = drawable.ic_setting),
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
                painter = painterResource(id = drawable.ic_weeks),
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
                painter = painterResource(id = drawable.ic_book),
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
                painter = painterResource(id = drawable.ic_help),
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

data class TasksList(
    /*todo*/
    val date: Int,
    val numOfTasks: Int,

)
data class StampsList(
    /*todo*/
    val date: Int,
    val numOfStamps: Int,

    )
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
                painter = painterResource(id = drawable.s_000),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = drawable.s_001),
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = drawable.tag_blue),
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
                painter = painterResource(id = drawable.tag_yellow),
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

@Composable
fun DateAndDeadline(/*date: Int, day: String,
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
                painter = painterResource(id = drawable.s_000),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = drawable.s_001),
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = drawable.tag_blue),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "DUE XXXXX",
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
                painter = painterResource(id = drawable.tag_yellow),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "DUE XXXXX",
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


@Composable
fun DatesAndTasks(mode: Boolean){ // 可能要用list傳資料進來
    LazyColumn( modifier = Modifier
        .fillMaxWidth()
        .absoluteOffset(x = (-4).dp, y = (-15).dp),
        contentPadding = PaddingValues(15.dp)){
        items(10){
            if (mode) DateAndTask()
            else DateAndDeadline()
        }
    }
}

@Composable
fun Todos(){ // 如果寫成函式好像沒辦法照hifi的間距拉
    Row(modifier = Modifier
        .padding(start = 30.dp)
        .background(color = Color.Black)){
        Image(
            painter = painterResource(id = drawable.tag_blue),
            contentDescription = null,
            )
        Text(text = "TODO XXXXX", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.secondary)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "1hr", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSecondary)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    FinalTheme{
//        MainPage()
//    }
//}
