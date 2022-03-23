package utils

import android.util.Log

const val SIXTY_SECONDS = 60

fun String.getSeconds() : Int {
    var count = 0
    this.forEach {
        if (it.toString() == ":") {
            count++
        }
    }
    return if (count == 1) {
        this.substringBefore(':').toInt() * SIXTY_SECONDS +
                this.substringAfter(':').toInt()
    } else {
        val timeAfterHours = this.substringAfter(':')
        this.substringBefore(':').toInt() * SIXTY_SECONDS * SIXTY_SECONDS +
                timeAfterHours.substringBefore(':').toInt() * SIXTY_SECONDS +
                timeAfterHours.substringAfter(':').toInt()
    }
}

fun step(description: String, action: () -> Unit) {
    val desc = description.ifEmpty { "empty desc" }
    Log.d("ALLURE step", desc)
    action()
}