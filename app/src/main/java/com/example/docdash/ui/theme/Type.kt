package com.example.docdash.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.docdash.R

val proximaNova = FontFamily(
    Font(R.font.proxima_nova_reg, FontWeight.Normal),
    Font(R.font.proxima_nova_bold, FontWeight.Bold),
    Font(R.font.proxima_nova_it, FontWeight.Normal)
)

val MyTypography = Typography(
    body1 = TextStyle(
        fontFamily = proximaNova,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
            // similarly, override other parameters like h2, subtitle, etc...
)

// Set of Material typography styles to start with

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
