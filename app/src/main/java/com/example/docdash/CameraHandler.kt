package com.example.docdash

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class CameraHandler {
    companion object{
        @Composable
        fun openCamera(): ManagedActivityResultLauncher<Intent, ActivityResult> {
            val context = LocalContext.current
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                // Camera result can be handled here
                }
            }
            return launcher
        }
    }
}