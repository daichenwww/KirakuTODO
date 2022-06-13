package com.example.afinal.feature_task.presentation.stampbook

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.afinal.R

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
                    )
                }
                "004"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_004),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
                "005"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_005),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
                "006"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_006),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
                "007"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_007),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
                "008"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_008),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
                "009"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_009),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
                "010"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_010),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
                "011"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_011),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
                "012"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_012),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
                "013"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_013),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
                "014"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_014),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
                "015"-> {
                    Image(
                        painter = painterResource(id = R.drawable.stamp_015),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp, 90.dp)
                    )
                }
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
