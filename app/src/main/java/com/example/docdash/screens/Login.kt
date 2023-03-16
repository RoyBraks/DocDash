package com.example.docdash.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.docdash.R
import com.example.docdash.queueTimeCircle.components.themeColor
import com.example.docdash.ui.theme.proximaNova

class Login(navController: NavHostController) {
    val navController = navController

    @Composable
    fun loginScreen(){
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val width = (screenWidth * 0.75f)
        val widthButton = (screenWidth * 0.6f)
        val height = (screenHeight * 0.15f)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.bg_main),
                    contentScale = ContentScale.FillWidth
                ),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .offset( 0.dp, -60.dp)
            ) {
                Text(
                    text = "Login",
                    fontSize = 50.sp,
                    color = Color(0xFF747474),
                    style = TextStyle(
                        fontFamily = proximaNova,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(140.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 8.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Button(
                        onClick = { navController.navigate("Homepage") },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(2.dp, Color(0xFF00CAD5)),
                        modifier = Modifier
                            .width(widthButton)
                            .height(56.dp)
                    ) {
                        Text(text = "Log in", color = themeColor.primaryColor)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(backgroundColor = themeColor.primaryColor),
                        modifier = Modifier
                            .width(widthButton)
                            .height(56.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(themeColor.primaryColor)
                    ) {
                        Text(text = "Register", color = Color.White)
                    }
                }
            }
        }
    }
}