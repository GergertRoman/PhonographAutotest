package utils.extention

import android.app.Activity
import android.content.pm.ActivityInfo

fun Activity.setEnableLandscapeMode(enable: Boolean, forceLandscape: Boolean = false) {
    val orientation = when {
        enable && forceLandscape -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        enable && !forceLandscape -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
        else -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    requestedOrientation = orientation
}
