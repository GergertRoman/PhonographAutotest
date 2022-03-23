package utils

import android.view.View
import android.widget.ProgressBar
import android.widget.SeekBar
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun getProgressForProgressBar(view: ViewInteraction): Int {
    var progress = 0
    val matcher = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("get progress value from progress bar")
        }

        override fun matchesSafely(item: View?): Boolean {
            when(item) {
                is ProgressBar -> progress = item.progress
                is SeekBar -> progress = item.progress
            }
            return true
        }
    }
    view.check(ViewAssertions.matches(matcher))
    return progress
}