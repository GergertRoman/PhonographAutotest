package common

import android.Manifest
import android.app.Activity
import androidx.test.rule.GrantPermissionRule
import com.kabouzeid.gramophone.ui.activities.MainActivity
import org.junit.Rule
import utils.extention.setEnableLandscapeMode

abstract class BaseTest {
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.READ_EXTERNAL_STORAGE,
    )

    @get:Rule
    var activityTestRule = lazyActivityScenarioRule<MainActivity>(launchActivity = true)

    private fun activity(function: Activity.() -> Unit) {
        activityTestRule.getScenario().onActivity { activity -> function.invoke(activity) }
    }

    fun pause(ms: Long) {
        Thread.sleep(ms)
    }

    fun setEnableLandscapeMode(enabled: Boolean, forceLandscape: Boolean = true) {
        activity {
            setEnableLandscapeMode(enabled, forceLandscape)
        }
    }
}
