package com.example.docdash

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.docdash.BoxUIelements.Companion.SmallBox
import com.example.docdash.location.LiveLocation
import com.example.docdash.location.PolygonCoordinates
import com.example.docdash.notifications.components.SetupNotifications
import com.example.docdash.notifications.viewmodel.NotificationViewModel
import com.example.docdash.queueTimeCircle.components.QueueTimeBox
import com.example.docdash.ui.theme.DocDashTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.docdash.notifications.permission.Companion.RequestNotificationPermission

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DocDashTheme {
                RequestNotificationPermission()
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
                            Spacer(modifier = Modifier.height(30.dp))
                            QueueTimeBox()
                            Spacer(modifier = Modifier.height(30.dp))
                            Boxes()
                            Spacer(modifier = Modifier.height(30.dp))
                            SetupNotifications(context = context, notificationViewModel = viewModel)
                        }
                    }
                }
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
    location.value?.let { SmallBox(it, PolygonCoordinates.polygonCoordinates) }
}
