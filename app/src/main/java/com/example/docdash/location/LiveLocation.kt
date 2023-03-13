package com.example.docdash.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil

class LiveLocation {
    companion object {
        const val REQUEST_CODE_LOCATION_PERMISSION = 1
        @Composable
        fun getCurrentLocation(): State<Location?> {
            val context = LocalContext.current
            val locationManager = remember(context) {
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            }

            // Define a mutable state variable to hold the location
            val location = remember { mutableStateOf<Location?>(null) }

            // Define a callback function to handle location updates
            val locationListener = object : LocationListener {
                override fun onLocationChanged(loc: Location) {
                    location.value = loc
                }

                override fun onProviderDisabled(provider: String) {}

                override fun onProviderEnabled(provider: String) {}

                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            }

            val criteria = remember {
                Criteria().apply {
                    accuracy = Criteria.ACCURACY_FINE
                    powerRequirement = Criteria.POWER_LOW
                }
            }


            // Request location updates when the composable is first launched
            LaunchedEffect(Unit) {
                try {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        locationManager.requestLocationUpdates(
                            2000L, // minimum time interval between location updates, in milliseconds
                            0f, // minimum distance between location updates, in meters
                            criteria,
                            locationListener,
                            Looper.getMainLooper()
                        )
                    } else {
                        // Request location permission
                        ActivityCompat.requestPermissions(
                            context as Activity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            REQUEST_CODE_LOCATION_PERMISSION
                        )
                    }
                } catch (e: Exception) {
                    // handle error
                }
            }
            // Pass the location to child composables
            return location
        }

        @Composable
        fun checkIfInPolygon(location: Location?, polygonCoordinates: List<List<LatLng>>): Boolean {

            var isInPolygon = PolyUtil.containsLocation(location?.let { LatLng(it.latitude, it.longitude) }, polygonCoordinates.flatten(), true)

            return isInPolygon
        }
    }
}