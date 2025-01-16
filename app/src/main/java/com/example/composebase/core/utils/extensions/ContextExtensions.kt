package com.example.composebase.core.utils.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.example.composebase.R


fun Context.goToAppSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts(getString(R.string.open_settings_uri), packageName, null)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    startActivity(intent)
}