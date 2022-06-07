import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.afinal.R


@Composable
fun HelpPage(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth().size(80.dp),
                title = {
                    Text(
                        text = "說明",
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .absoluteOffset((-30).dp, 0.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_return),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .absoluteOffset(x = 5.dp)
                        )
                    }
                }
            )
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ){
            Help("自動排程是什麼",
                "ji3ji3jihiehothoiaevuoewuvt08eututp8vay8eyywrvetvaet")
            Help("為何輸入的任務被寫在不同的日期",
                "ji3ji3jihiehothoiaevuoewuvt08eututp8vay8eyywrvetvaet")
            Help("如何評估與填寫舒適工時",
                "ji3ji3jihiehothoiaevuoewuvt08eututp8vay8eyywrvetvaet")
            Help("如何獲得貓貓印章",
                "ji3ji3jihiehothoiaevuoewuvt08eututp8vay8eyywrvetvaet")
            Help("如何擁有新種類的貓貓印章",
                "ji3ji3jihiehothoiaevuoewuvt08eututp8vay8eyywrvetvaet")
        }
    }
}

@Composable
fun Help(title: String, content: String){
    Column( modifier = Modifier.fillMaxWidth().padding(top = 20.dp))
    {
        val selected = remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp
                ),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                selected.value = !selected.value
            }) {
                Image(
                    painter = if (selected.value) painterResource(id = R.drawable.up_arrow)
                    else painterResource(id = R.drawable.down_arrow),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            }
            Text(
                text = "$title",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        if (selected.value) {
            Text(
                text = "$content",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(start = 60.dp, end = 20.dp, top = 10.dp)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HelpScreenPreview() {
//    FinalTheme{
//        HelpPage()
//    }
//}