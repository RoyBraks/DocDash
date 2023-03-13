package com.example.docdash

import android.app.Application
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.docdash.BoxUIelements.Companion.SmallBox
import com.example.docdash.location.LiveLocation
import com.example.docdash.location.LiveLocation.Companion.checkIfInPolygon
import com.example.docdash.location.PolygonCoordinates
import com.example.docdash.notifications.components.SetupNotifications
import com.example.docdash.notifications.viewmodel.NotificationViewModel
import com.example.docdash.queueTimeCircle.components.QueueTimeBox
import com.example.docdash.ui.theme.DocDashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocDashTheme {
                val owner = LocalViewModelStoreOwner.current

                owner?.let {
                    val viewModel: NotificationViewModel = viewModel(
                            it,
                            "NotificationViewModel",
                            NotificationViewModelFactory(
                                    LocalContext.current.applicationContext as Application
                            )
                    )
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
                            Boxes()
                            SetupNotifications(context = context, notificationViewModel = viewModel)
                        }


                    }
                }
                // A surface container using the 'background' color from the theme
            }
        }
    }
    class NotificationViewModelFactory(val application: Application) :
            ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NotificationViewModel(application) as T
        }
    }
}

@Composable
fun Boxes(){
    val location = LiveLocation.getCurrentLocation()
//    val location = locationState.value
    location.value?.let { SmallBox(it, PolygonCoordinates.polygonCoordinates) }
//    SmallBox(location = location, polygonCoordinates = PolygonCoordinates.polygonCoordinates )
}


