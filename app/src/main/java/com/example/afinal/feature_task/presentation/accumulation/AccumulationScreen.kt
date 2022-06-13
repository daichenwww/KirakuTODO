import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.afinal.R
import com.example.afinal.R.drawable
import com.example.afinal.feature_task.presentation.accumulation.AccumulationViewModel
import com.example.afinal.feature_task.presentation.common.shareImageText
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

@Composable
fun AccumulationPage(
    navController: NavController,
    viewModel: AccumulationViewModel = hiltViewModel()
) {
    val CurDate = viewModel.CurDate
    val CurDateShift7 = viewModel.CurDateShift7
    val CurDateShift14 = viewModel.CurDateShift14
    val CurDateShift21 = viewModel.CurDateShift21
    val CurDateShift28 = viewModel.CurDateShift28

    val doneTaskNumIn7 = viewModel.doneTaskNumIn7
    val doneTaskNumIn14 = viewModel.doneTaskNumIn14
    val doneTaskNumIn21 = viewModel.doneTaskNumIn21
    val doneTaskNumIn28 = viewModel.doneTaskNumIn28
    var maxDoneInMonth = viewModel.maxDoneInMonth.toFloat()
    if(maxDoneInMonth==0F) maxDoneInMonth = 1F

    val ratio28 = (doneTaskNumIn28*9/maxDoneInMonth).roundToInt()
    val ratio21 = (doneTaskNumIn21*9/maxDoneInMonth).roundToInt()
    val ratio14 = (doneTaskNumIn14*9/maxDoneInMonth).roundToInt()
    val ratio7 = (doneTaskNumIn7*9/maxDoneInMonth).roundToInt()


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp),
                title = {
                    Text(
                        text = "累計",
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .absoluteOffset((-25).dp, 0.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth()) {
                Image(
                    painter = painterResource(id = drawable.booksbackground2),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                )
                Text(
                    text = "各周累計",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .absoluteOffset(x = (-15).dp, y = 50.dp),

                    )
                Text(
                    text = "完成任務數",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
//                        .size(340.dp, 70.dp)
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .absoluteOffset(x = (-15).dp, y = 90.dp),

                    )
                Row(
                    modifier = Modifier
//                        .size(340.dp, 700.dp)
                        .fillMaxWidth(0.75f)
                        .fillMaxHeight(0.8f)
                        .align(Alignment.BottomCenter)
                        .absoluteOffset(x = (-25).dp, y = (-80).dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    StampsOfAWeek(false, CurDateShift21, CurDateShift28, ratio28, doneTaskNumIn28)
                    StampsOfAWeek(false, CurDateShift14, CurDateShift21, ratio21, doneTaskNumIn21)
                    StampsOfAWeek(false, CurDateShift7, CurDateShift14, ratio14, doneTaskNumIn14)
                    StampsOfAWeek(true, CurDate, CurDateShift7, ratio7, doneTaskNumIn7)
                }
                Row(
                    modifier = Modifier
                        .size(150.dp, 30.dp)
                        .align(Alignment.BottomCenter)
                        .absoluteOffset(x = (-15).dp, y = (-40).dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // 上一頁
                    /*
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = if (true) painterResource(id = R.drawable.last)
                            else painterResource(id = R.drawable.last_no),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .absoluteOffset(x = 5.dp)
                        )
                    }
                    // 下一頁
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = if (true) painterResource(id = R.drawable.next_no)
                            else painterResource(id = R.drawable.next),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .absoluteOffset(x = 5.dp)
                        )
                    }

                     */
                }
            }
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .absoluteOffset(x = (-15).dp)
            ) {
                Button(shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onBackground),
                    onClick = {navController.navigate("help")},

                    ) {
                    Text(
                        text = "說明",
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = Modifier.size(80.dp, 2.dp))
                val context = LocalContext.current
                Button( shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onBackground),
                    onClick = { /*TODO*/
                        shareImageText(context)
                    }
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
fun StampsOfAWeek(
    thisWeek: Boolean, startDate: String, endDate: String,
    ratioOfBar: Int /*use this to change the img*/,
    number: Int
) {
    val img = when (ratioOfBar) {
        1 -> painterResource(id = drawable.bar_1)
        2 -> painterResource(id = drawable.bar_2)
        3 -> painterResource(id = drawable.bar_3)
        4 -> painterResource(id = drawable.bar_4)
        5 -> painterResource(id = drawable.bar_5)
        6 -> painterResource(id = drawable.bar_6)
        7 -> painterResource(id = drawable.bar_7)
        8 -> painterResource(id = drawable.bar_8)
        9 -> painterResource(id = drawable.bar_9)
        else -> painterResource(id = drawable.bar_0)
    }
    val offset = when (ratioOfBar) {
        1 -> 28
        2 -> 48
        3 -> 86
        4 -> 123
        5 -> 160
        6 -> 190
        7 -> 217
        8 -> 254
        9 -> 276
        else -> 25
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(
            painter = img,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .size(70.dp)
                .absoluteOffset(y = 40.dp)
        )
        Text(
            text = number.toString(),
            color = colors.onSecondary,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .absoluteOffset(y = -offset.dp)
                .rotate(-6.49f)
        )
        if (thisWeek) {
            Text(
                text = "本周",
                color = colors.onSecondary,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.absoluteOffset(y = 10.dp)
            )
            Text(
                text = " ",
                color = colors.onSecondary,
                style = MaterialTheme.typography.body2
            )
        } else {
            Text(
                text = "$startDate~",
                color = colors.onSecondary,
                style = MaterialTheme.typography.body2
            )
            Text(
                text = "$endDate",
                color = colors.onSecondary,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.absoluteOffset(y = (-10).dp)
            )
        }

    }
}


//@Preview
//@Composable
//fun AccumulationPreview() {
//    FinalTheme{
//        AccumulationPage("1")
//    }
//}
