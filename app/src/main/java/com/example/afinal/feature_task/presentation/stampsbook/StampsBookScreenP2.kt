import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.R
import com.example.afinal.feature_task.presentation.common.shareImageText
import com.example.afinal.feature_task.presentation.stampbook.Meow
import com.example.afinal.feature_task.presentation.stampbook.StampBookViewModel


@Composable
fun StampsBookPageP2(navController: NavController,
                     viewModel: StampBookViewModel = hiltViewModel()
){
    val doneTaskNum = viewModel.doneTaskNum
    val page ="2"
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
                        Meow("006", (doneTaskNum<30), "Finish 30 todo")
                        Meow("007", (doneTaskNum<50), "Finish 50 todo")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(0.75f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Meow("008", (doneTaskNum<75), "Finish 75 todo")
                        //Spacer(modifier = Modifier.width(10.dp))
                        Meow("009", (doneTaskNum<100), "Finish 100 todo")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(0.75f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Meow("010", (doneTaskNum<150), "Finish 150 todo")
                        //Spacer(modifier = Modifier.width(10.dp))
                        Meow("011", (doneTaskNum<200), "Finish 200 todo")
                    }
                    Row(
                        modifier = Modifier
                            .size(150.dp, 30.dp)
                            .absoluteOffset(x = 0.dp, y = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // navigate to prev page
                        IconButton(onClick = { navController.navigate("stampsbookp1") }) {
                            if (page == "1"){
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
                            text = "/ 3",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSecondary
                        )
                        IconButton(onClick = { navController.navigate("stampsbookp3") }) {
                            if (page != "3"){
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
                    onClick = {navController.navigate("help")},
                ) {
                    Text(
                        text = "說明",
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body1
                    )
                }
                val context = LocalContext.current
                Spacer(modifier = Modifier.size(80.dp, 2.dp))
                Button( shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onBackground),
                    onClick = { /*TODO*/
                        shareImageText(context)
                    }                ) {
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



//@Preview
//@Composable
//fun StampPreview() {
//    FinalTheme{
//        StampsBookPage("1")
//    }
//}
