package com.example.android_logger

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log

class AppInstallLogger : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_PACKAGE_ADDED) {
            val packageName = intent.data?.encodedSchemeSpecificPart
            val appName = getInstalledAppName(context, packageName)
            Log.d("com.example.android_logger.AppInstallLogger", "Installed: $appName ($packageName)")
        }
    }



    private fun getInstalledAppName(context: Context?, packageName: String?): String {
        // Check if context is not null before accessing packageManager
        val packageManager = context?.packageManager ?: return "Unknown"

        val applicationInfo: ApplicationInfo? = packageName?.let {
            try {
                packageManager.getApplicationInfo(it, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                null
            }
        }

        return applicationInfo?.loadLabel(packageManager).toString()
    }



}
