package com.example.docdash.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.docdash.BoxUIelements
import com.example.docdash.R
import com.example.docdash.ui.theme.DocDashTheme

class login {
    @Preview
    @Composable
    fun loginScreen(){
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val width = (screenWidth * 0.75f)
        val height = (screenHeight * 0.15f)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.bg_main),
                    contentScale = ContentScale.FillWidth
                )
        ){
            Column() {
                Text(
                    text = "DocDash",
                    fontSize = 30.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .offset(x = 0.dp, y = -150.dp)
                )

                Box(
                    modifier = Modifier
                        .width(width)
                        .height(40.dp)
                        .clip(RoundedCornerShape(20.dp))

                ){
                    Text(text = "Log in")
                }
            }
        }
    }
}