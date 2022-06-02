import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.afinal.R
import com.example.afinal.ui.theme.*
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.PrintFormat

@Composable
fun SettingPage(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth().size(80.dp),
                title = {
                    Text(
                        text = "設定",
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
        ) {
            Setting("舒適工時", "平日2小時, 假日8小時", false, false)
            Setting("提醒", "上午 09:00, 下午 18:00", true, true)
            Setting("字體大小", "中", false, false)
            Setting("深色模式", "", true, false)
            Setting("邀請碼", "197498327085", false, false)
        }
    }
}

@Composable
fun Setting(title: String, value: String, isSwitch: Boolean, switchOn: Boolean){
    Row(
        modifier = Modifier
            .size(395.dp, 80.dp)
            .padding(
                start = 20.dp,
                end = if (isSwitch) 12.dp
                else 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        val selected = remember { mutableStateOf(switchOn) }
        Text(
            text = "$title",
            style = MaterialTheme.typography.h3,
            color =  MaterialTheme.colors.onSecondary
        )
        Text(
            text = if (isSwitch and !selected.value) "" else "$value",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.secondary
        )
        if (isSwitch) {
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
    }
}

////@Preview(showBackground = true)
//@Composable
//fun SettingScreenPreview() {
//    FinalTheme{
//        SettingPage()
//    }
//}