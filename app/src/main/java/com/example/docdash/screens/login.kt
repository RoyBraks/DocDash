package com.example.docdash.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.docdash.BoxUIelements
import com.example.docdash.R
import com.example.docdash.ui.theme.DocDashTheme

class login {
    @Preview
    @Composable
    fun loginScreen(){
        Box(
            modifier = Modifier
            .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.bg_main),
                    contentScale = ContentScale.FillWidth
                )
        ){
            Text(text = "DocDash")
        }
    }
}