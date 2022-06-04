import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.afinal.R
import com.example.afinal.ui.theme.*

@Composable
fun AddTaskPage(navController: NavController){
    Scaffold(
        topBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier.fillMaxWidth().size(80.dp),
            ) {
                TextButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    onClick = {/*TODO*/navController.popBackStack()},
                    modifier = Modifier
                        .size(100.dp, 60.dp)
                        .absoluteOffset(x = (-5).dp)
                ) {
                    Text(
                        text = "取消",
                        color = MaterialTheme.colors.background,
                        style = MaterialTheme.typography.h3
                    )
                }
                Text( /*pass parameter to decide edit or add*/
                    text = if (true) "新增任務" else "編輯任務",
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.size(210.dp, 45.dp)
                )
                TextButton(
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(100.dp, 60.dp)
                        .absoluteOffset(x = (5).dp)
                ) {
                    Text(
                        text = "完成",
                        color = MaterialTheme.colors.background,
                        style = MaterialTheme.typography.h3
                    )
                }

            }
        }
    ){
        Column(){
            Row(
                modifier = Modifier
                    .fillMaxWidth().size(80.dp)
                    .padding(top = 20.dp, start = 20.dp, end = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "標題",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.body1
                )
                var text by remember { mutableStateOf("軟實軟實軟實 ") }
                TextField(
                    modifier = Modifier.size(250.dp, 65.dp),
                    value = text,
                    onValueChange = { text = it },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background,
                        textColor = MaterialTheme.colors.onSecondary,
                        focusedIndicatorColor = MaterialTheme.colors.onSecondary, /*點擊之後的底線顏色*/
                        unfocusedIndicatorColor = MaterialTheme.colors.onSecondary/*點擊之前的底線顏色*/),

                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth().size(80.dp)
                    .padding(start = 20.dp, end = 60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "到期日",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.body1
                )
                var text by remember { mutableStateOf("2022年 6月14號") }
                TextField(
                    modifier = Modifier.size(220.dp, 65.dp),
                    value = text,
                    onValueChange = { text = it },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background,
                        textColor = MaterialTheme.colors.onSecondary,
                        focusedIndicatorColor = MaterialTheme.colors.onSecondary, /*點擊之後的底線顏色*/
                        unfocusedIndicatorColor = MaterialTheme.colors.onSecondary/*點擊之前的底線顏色*/)
                    )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth().size(80.dp)
                    .padding(start = 30.dp, end = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                val selected1 = remember { mutableStateOf(false) }
                val selected2 = remember { mutableStateOf(false) }
                val selected3 = remember { mutableStateOf(false) }
                Button( modifier = Modifier.size(75.dp, 55.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selected1.value) RedTag else LightRedTag
                    ),
                    onClick = { /*TODO*/
                        selected1.value = !selected1.value
                    }
                ) {
                    Text(
                        text = "報告",
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body2
                    )
                }
                Button( modifier = Modifier.size(75.dp, 55.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selected2.value) YellowTag else LightYellowTag
                    ),
                    onClick = { /*TODO*/
                        selected2.value = !selected2.value
                    }
                ) {
                    Text(
                        text = "作業",
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body2
                    )
                }
                Button( modifier = Modifier.size(75.dp, 55.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selected3.value) BlueTag else LightBlueTag
                    ),
                    onClick = { /*TODO*/
                        selected3.value = !selected3.value
                    }
                ) {
                    Text(
                        text = "休閒",
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body2
                    )
                }
                Button( modifier = Modifier.size(75.dp, 55.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.onBackground
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }
            val selected = remember { mutableStateOf(true) }
            Row(
                modifier = Modifier
                    .fillMaxWidth().size(80.dp)
                    .padding(start = 20.dp, end = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "自動排程",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.body1
                )
                IconButton(onClick = { /*TODO*/
                    selected.value = !selected.value
                }) {
                    Image(
                        painter =   if (selected.value) painterResource(id = R.drawable.swon)
                                    else painterResource(id = R.drawable.swoff),
                        contentDescription = null,
                        modifier = Modifier.size(65.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth().size(80.dp)
                    .padding(
                        start = 20.dp,
                        end = if (selected.value) 200.dp
                              else 70.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                if (selected.value) {
                    Text(
                        text = "預計費時",
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body1
                    )
                    var text by remember { mutableStateOf("2 hr") }
                    TextField(
                        modifier = Modifier.size(80.dp, 65.dp),
                        value = text,
                        onValueChange = { text = it },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.background,
                            textColor = MaterialTheme.colors.onSecondary,
                            focusedIndicatorColor = MaterialTheme.colors.onSecondary, /*點擊之後的底線顏色*/
                            unfocusedIndicatorColor = MaterialTheme.colors.onSecondary/*點擊之前的底線顏色*/)
                    )
                }
                else {
                    Text(
                        text = "安排日",
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body1
                    )
                    var text by remember { mutableStateOf("2022年 6月 2號") }
                    TextField(
                        modifier = Modifier.size(210.dp, 65.dp),
                        value = text,
                        onValueChange = { text = it },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.background,
                            textColor = MaterialTheme.colors.onSecondary,
                            focusedIndicatorColor = MaterialTheme.colors.onSecondary, /*點擊之後的底線顏色*/
                            unfocusedIndicatorColor = MaterialTheme.colors.onSecondary/*點擊之前的底線顏色*/)
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AddTaskScreenPreview() {
//    FinalTheme{
//        AddTaskPage()
//    }
//}