package com.example.docdash.queueTimeCircle.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.docdash.ThemeColors
import com.example.docdash.queueTimeCircle.utils.Utility
import com.example.docdash.queueTimeCircle.utils.Utility.formatTime
import com.example.docdash.queueTimeCircle.viewmodel.ViewModelTimer


var themeColor = ThemeColors()

@Composable
fun QueueTimeBox(viewModel: ViewModelTimer = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val time by viewModel.time.observeAsState(Utility.TOTAL_TIME.formatTime())
    val progress by viewModel.progress.observeAsState(1.00F)
    val isPlaying by viewModel.isPlaying.observeAsState(false)
    val listPosition by viewModel.listPosition.observeAsState(null)

    QueueTimeBox(
        time = time,
        progress = progress,
        isPlaying = isPlaying,
        listPosition = listPosition
    )
}

@Composable
fun QueueTimeBox(
    time: String,
    progress: Float,
    isPlaying: Boolean,
    listPosition: Int?,
    viewModel: ViewModelTimer = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    )

    if(!isPlaying) {
        viewModel.handleCountDownTimer()
    }

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(size = 320.dp)
                    .background((themeColor.primaryColor)),
                onDraw = {
                    drawCircle(
                        color = Color.White,
                        radius = 150.dp.toPx()
                    )

                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                themeColor.primaryColor,
                                themeColor.primaryColorLight
                            )
                        ),
                        radius = 140.dp.toPx()
                    )
                }
            )
            CircularProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp),
                strokeWidth = 10.dp,
                color = themeColor.primaryOrange
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,

                modifier = Modifier
                    .width(IntrinsicSize.Max)
            ) {
                Text(
                    text = time,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                )
                Text(
                    buildAnnotatedString {
                        append("You are ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold) ){
                            append("$listPosition")
                            append(
                                when (listPosition) {
                                    1, 21, 31 -> "st "
                                    2, 22, 32 -> "nd "
                                    3, 23, 33 -> "rd "
                                    else -> {"th "}
                                }
                            )
                        }
                        append("in queue")
                    },
                    color = Color.White
                )
            }
        }
    }
}


