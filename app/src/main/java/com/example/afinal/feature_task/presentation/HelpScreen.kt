import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.input.key.Key.Companion.Help
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.afinal.R

@ExperimentalAnimationApi
@Composable
fun HelpPage(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp),
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
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Top,
        ){
            item{
                Help(
                    "自動排程是什麼",
                    "KirakuTodo會根據您所填寫的每日舒適工時與每項任務的估計時長，將這些任務拆成多個todo，自動分布在到期日之前，大大的降低事情截止日前的混亂與壓力。"
                )
            }
            item{
                Help("為何輸入的任務被寫在不同的日期",
                    "KirakuTodo會將您填寫的任務根據自動排程，以代辦事項的形式分別安排在不同的日期。")
            }
           item{
               Help("如何評估與填寫舒適工時",
               "舒適工做時長是KirakuTodo為您安排任務的主要衡量標準，填寫您每天估計能有多少時間來完成任務。您可以在導覽列中的設定頁面裡找到舒適工時的填寫欄位。")
           }
            item{
                Help("如何獲得貓貓印章",
                "每完成一個任務就可以獲得一個貓貓印章，快去完成任務吧！")
            }
            item{
                Help("如何擁有新種類的貓貓印章",
                    "達成特定的條件就可以解鎖心的貓貓印章，您可以在導覽列中的圖鑑頁面裡查看更多。")
            }
        }
    }
}

@Composable
fun Help(title: String, content: String){
    Column( modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp))
    {
        var selected by remember { mutableStateOf(false) }
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
            IconButton(onClick = { selected = !selected }) {
                Crossfade(
                    targetState = selected,
                    animationSpec = tween(200)) { selected->
                        when (selected) {
                            true -> Image(
                                painter =painterResource(id = R.drawable.up_arrow),
                                contentDescription = null,
                                modifier = Modifier.size(35.dp)
                            )
                            else -> Image(
                               painterResource(id = R.drawable.down_arrow),
                                contentDescription = null,
                                modifier = Modifier.size(35.dp)
                            )
                        }
                }
            }
            Text(
                text = "$title",
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Crossfade(
            targetState = selected,
            animationSpec = tween(200)) { selected->
            when (selected) {
                true -> Text(
                    text = "$content",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(start = 60.dp, end = 20.dp, top = 10.dp)
                )
                else -> {}
            }
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