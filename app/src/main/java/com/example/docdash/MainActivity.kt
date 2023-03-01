package com.example.docdash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.docdash.ui.theme.DocDashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocDashTheme {
                // A surface container using the 'background' color from the theme
            }
        }
    }
}

@Composable
fun QueueTimeBox(time: String, listPosition: Int) {
    Box() {
        Canvas(
            modifier = Modifier
                .clip(shape = CircleShape),

            onDraw =  {
            drawCircle(
                color = Color.White)
        })
    }
}

@Preview
@Composable
fun DefaultPreview() {
    QueueTimeBox(time = "00:14:37", listPosition = 2)
}
