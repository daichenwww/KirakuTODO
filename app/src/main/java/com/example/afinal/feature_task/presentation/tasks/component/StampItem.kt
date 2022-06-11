package com.example.afinal.feature_task.presentation.tasks.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.afinal.R

@Composable
// input: Stamp id
// determined in finish state...?
fun Stamp(stampId: String){
    Spacer(modifier = Modifier.width(8.dp))
    when (stampId){
        "000" -> {
            Image(
                painter = painterResource(id = R.drawable.stamp_000),
                contentDescription = null,
                modifier = Modifier.size(70.dp, 45.dp)
            )
        }"001"-> {
        Image(
            painter = painterResource(id = R.drawable.stamp_001),
            contentDescription = null,
            modifier = Modifier.size(60.dp, 45.dp)
        ) }
        "002" -> {
            Image(
                painter = painterResource(id = R.drawable.stamp_002),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            ) }
        "003"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_003),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        "004"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_004),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        "005"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_005),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        "006"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_006),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        "007"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_007),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        "008"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_008),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        "009"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_009),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        "010"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_010),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        "011"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_011),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        "012"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_012),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        "013"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_013),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        "014"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_014),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        "015"-> {
            Image(
                painter = painterResource(id = R.drawable.stamp_015),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
        else ->{
            Image(
                painter = painterResource(id = R.drawable.stamp_undefined),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 45.dp)
            )
        }
    }

}