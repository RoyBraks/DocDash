package com.example.docdash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.docdash.notifications.components.AddNotificationBox
import com.example.docdash.queueTimeCircle.components.QueueTimeBox
import com.example.docdash.ui.theme.DocDashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DocDashTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .paint(
                            painter = painterResource(id = R.drawable.bg_main),
                            contentScale = ContentScale.FillWidth
                        )
                ) {
                    val context = LocalContext.current
                    Column {
                        QueueTimeBox()
                    }

                }
            }
            AddNotificationBox(context = this, owner = this)

        }
    }

}
//
//@Preview
//@Composable
//fun DefaultPreview() {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .paint(
//                painter = painterResource(id = R.drawable.bg_main),
//                contentScale = ContentScale.FillWidth
//            )
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            QueueTimeBox(time = "00:14:45", listPosition = 2)
//        }
//    }
//}