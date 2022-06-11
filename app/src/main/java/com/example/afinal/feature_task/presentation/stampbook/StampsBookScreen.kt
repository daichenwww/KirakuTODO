import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.R
import com.example.afinal.feature_task.presentation.stampbook.StampBookViewModel


@Composable
fun StampsBookPage(navController: NavController,
                   viewModel: StampBookViewModel = hiltViewModel()
){
    val doneTaskNum = viewModel.doneTaskNum
    val page = viewModel.page
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth().size(80.dp),
                title = {
                    Text(
                        text = "圖鑑",
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .absoluteOffset((-25).dp, 0.dp)
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
    )
        { // main content
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Box(modifier = Modifier.fillMaxHeight(0.9f).fillMaxWidth())
                {
                    Image(
                        painter = painterResource(id = R.drawable.booksbackground2),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize()
                    )
                    Column(
                        modifier = Modifier
                            //.padding(top = 10.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(0.95f)
                            .align(Alignment.Center)
                            .absoluteOffset(x = (-20).dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(0.75f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            when(page){
                                "1"->{
                                    Meow("000", false, "Kiraku TODO!")
                                    Meow("001", false, "Finish 1 todo")
                                }
                                else ->{
                                    Meow("006", false, "Kiraku TODO!")
                                    Meow("007", false, "Finish 1 todo")
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(0.75f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Meow("002", false, "Finish 5 todo")
                            //Spacer(modifier = Modifier.width(10.dp))
                            Meow("003", false, "Finish 10 todo")
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(0.75f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Meow("004", false, "Finish 15 todo")
                            //Spacer(modifier = Modifier.width(10.dp))
                            Meow("005", true, "Finish 20 todo")
                        }
                        Row(
                            modifier = Modifier
                                .size(150.dp, 30.dp)
                                .absoluteOffset(x = 0.dp, y = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // navigate to next page
                            IconButton(onClick = { viewModel.prevPage() }) {
                                if (page > "1"){
                                    Image(
                                        painter = painterResource(id = R.drawable.last_no),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .absoluteOffset(x = 5.dp)
                                    ) }
                                else {
                                    Image(
                                        painter = painterResource(id = R.drawable.last),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .absoluteOffset(x = 5.dp)
                                    )
                                }
                            }
                            Text(
                                text = page,
                                style = MaterialTheme.typography.body2,
                                color = MaterialTheme.colors.onSecondary
                            )
                            Text(
                                text = "/ 10",
                                style = MaterialTheme.typography.body2,
                                color = MaterialTheme.colors.onSecondary
                            )
                            IconButton(onClick = { /*TODO*/ }) {
                                if (page < "3"){
                                    Image(
                                        painter = painterResource(id = R.drawable.next),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .absoluteOffset(x = 5.dp)
                                    ) }
                                else {
                                    Image(
                                        painter = painterResource(id = R.drawable.next_no),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(40.dp)
                                            .absoluteOffset(x = 5.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .absoluteOffset(x = (-15).dp)
                ) {
                    Button( shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onBackground),
                        onClick = { /*TODO*/ } // navigate to 說明頁面
                    ) {
                        Text(
                            text = "說明",
                            color = MaterialTheme.colors.onSecondary,
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Spacer(modifier = Modifier.size(80.dp, 2.dp))
                    Button( shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onBackground),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(
                            text = "分享",
                            color = MaterialTheme.colors.onSecondary,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
    }
}

@Composable
fun Meow(number: String, locked: Boolean, unlockedCondition: String, /*image: ?*/){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (!locked) {
            when (number) {
                "000" -> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_000),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }"001"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_001),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    ) }
                "002" -> {
                Image(
                    painter = painterResource(id = R.drawable.stamp_002),
                    contentDescription = null,
                    modifier = Modifier.size(140.dp, 90.dp)
                ) }
                "003"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_003),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                )}
                "004"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_004),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                )}
                "005"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_005),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )}
                "006"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_006),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )}
                "007"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_007),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )}
                "008"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_008),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )}
                "009"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_009),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )}
                "010"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_010),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )}
                "012"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_012),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )}
                "013"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_013),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )}
                "014"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_014),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )}
                "015"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_015),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )}
                else ->{
                    Image(
                        painter = painterResource(id = R.drawable.stamp_undefined),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
            }
        }
        else Image(
                painter = painterResource(id = R.drawable.stamp_undefined),
                contentDescription = null,
            modifier = Modifier.size(140.dp, 90.dp)
        )
        Row(){
            Text(text = "No.", style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSecondary)
            Text(text = number, style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSecondary) //編號
        }
        Row(){
            if (locked) Image(
                painter = painterResource(id = R.drawable.lock),
                contentDescription = null,
                modifier = Modifier.padding(5.dp)
            )
            else Image(
                painter = painterResource(id = R.drawable.unlock),
                contentDescription = null,
                modifier = Modifier.padding(5.dp)
            )
            Text(text = unlockedCondition, style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSecondary) // 解鎖條件
        }
    }
}



//@Preview
//@Composable
//fun StampPreview() {
//    FinalTheme{
//        StampsBookPage("1")
//    }
//}
