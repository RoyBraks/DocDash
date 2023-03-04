package com.example.docdash

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.docdash.ui.theme.DocDashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocDashTheme {
               Box(
//                   painter = painterResource(id = R.drawable.bg_main)
                   modifier = Modifier
                       .fillMaxSize()
                       .background(Color(0xFFFFFFFF))
                       .paint(
                           painter = painterResource(id = R.drawable.bg_main),
                           contentScale = ContentScale.FillWidth
                       )
               ){

               }
                SmallBox()
            }
        }
    }
}

var themeColor = ThemeColors()

@Composable
fun SmallBox() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val width = (screenWidth * 0.38f)
    val height = (screenHeight * 0.15f)
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .width(width)
                .height(height)
                .background(Color.White)
    //            .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .padding(start = 12.dp)
            ) {
            Text(
                text = "Afspraak",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = themeColor.primaryColor,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Dr. Peperkoek",
                fontWeight = FontWeight.Bold,
            )
                Spacer(modifier = Modifier.height(9.dp))
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black, fontSize = 14.sp)) {
                    append("Woensdag 15 februari ")
                }
                withStyle(style = SpanStyle(color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Bold)) {
                    append("15:00")
                }
            }
//                text = "Woensdag 15 februari",
//                fontSize = 14.sp
            )
                Text(
                    text = "15:00",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
        Spacer(modifier = Modifier.width(22.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .width(width)
                .height(height)
                .background(Color.White)
        ) {
            Text(text = "SCAN QR")


        }
    }
}