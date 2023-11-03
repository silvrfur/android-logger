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
            Log.d("AppInstallLogger", "Installed: $appName ($packageName)")
        }
    }

    private fun getInstalledAppName(context: Context?, packageName: String?): String {
        val packageManager = context?.packageManager
        val applicationInfo: ApplicationInfo? = packageName?.let {
            try {
                packageManager?.getApplicationInfo(it, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                null
            }
        }
        return applicationInfo?.loadLabel(packageManager).toString()
    }

}
