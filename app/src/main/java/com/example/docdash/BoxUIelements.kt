package com.example.docdash

import android.content.Intent
import android.location.Location
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.docdash.location.LiveLocation.Companion.checkIfInPolygon
import com.google.android.gms.maps.model.LatLng

class BoxUIelements {

    companion object{
        @Composable
        fun SmallBox(location: Location?, polygonCoordinates: List<List<LatLng>>) {
            val inZone = checkIfInPolygon(location, polygonCoordinates)
            var themeColor = ThemeColors()
            val screenWidth = LocalConfiguration.current.screenWidthDp.dp
            val screenHeight = LocalConfiguration.current.screenHeightDp.dp
            val width = (screenWidth * 0.38f)
            val height = (screenHeight * 0.15f)

            var i by remember { mutableStateOf(0) }


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
                        .clickable {
//                            i = if (!zone){
//                                0
//                            } else{
//                                1
//                            }
                            println(inZone)
                            i = if (!inZone && i == 1) {
                                0
                            } else {
                                1
                            }
                        }
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

                        )
                    }
                }
                Spacer(modifier = Modifier.width(22.dp))
                zoneActivityChecker(i = i, CameraHandler.openCamera())
            }
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .offset(x = 150.dp, y = 670.dp)
            ) {
//                Switch(
//                    checked = i == 1,
//                    onCheckedChange = { isChecked ->
//                        i = if (isChecked) 1 else 0
//                    },
//                    modifier = Modifier
//                        .align(Alignment.BottomCenter)
//                        .padding(16.dp)
//                )
            }
        }

        @Composable
        fun zoneActivityChecker(i: Int, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>){
            var themeColor = ThemeColors()
            val screenWidth = LocalConfiguration.current.screenWidthDp.dp
            val screenHeight = LocalConfiguration.current.screenHeightDp.dp
            val width = (screenWidth * 0.38f)
            val height = (screenHeight * 0.15f)

            if (i ==1){
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .width(width)
                        .height(height)
                        .background(Color.White)
                        .clickable {
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            launcher.launch(intent)
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .padding(start = 12.dp),
                    ) {
                        Text(
                            text = "SCAN QR",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = themeColor.primaryColor,
                        )
                        Image(
                            painter = painterResource(id = R.drawable.qr_code),
                            contentDescription = "QR Code",
                            modifier = Modifier
                                .size(65.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 10.dp)
                        )
                    }
                }
            }else{
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .width(width)
                        .height(height)
                        .background(Color.Gray)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .padding(start = 12.dp),
                    ) {
                        Text(
                            text = "SCAN QR",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = themeColor.primaryColor,
                        )
                        Image(
                            painter = painterResource(id = R.drawable.qr_code),
                            contentDescription = "QR Code",
                            modifier = Modifier
                                .size(65.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 10.dp)
                        )
                    }
                }
            }
        }
    }
}