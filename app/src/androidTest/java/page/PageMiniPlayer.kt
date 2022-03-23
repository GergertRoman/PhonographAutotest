package page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.atiurin.ultron.custom.espresso.matcher.withDrawable
import com.atiurin.ultron.extensions.*
import com.atiurin.ultron.page.Page
import com.kabouzeid.gramophone.R
import utils.getProgressForProgressBar

object PageMiniPlayer : Page<PageMiniPlayer>() {
    private val miniPlayer = withId(R.id.mini_player_fragment)
    private val slidingBtn = withId(R.id.mini_player_image)
    private val trackName = withId(R.id.mini_player_title)
    private val playPauseBtn = withId(R.id.mini_player_play_pause_button)
    private val trackProgress = withId(R.id.progress_bar)

    fun clickPlayerPauseBtn() {
        playPauseBtn.apply {
            isClickable()
            click()
        }
    }

    fun clickOnMiniPlayer() {
        miniPlayer.click()
    }

    fun checkPlayerPauseBtn(isPlayed: Boolean) {
        playPauseBtn.apply {
            isDisplayed()
            isEnabled()
            isClickable()
            if (isPlayed) {
                withDrawable(R.drawable.ic_pause_white_24dp)
            } else {
                withDrawable(R.drawable.ic_play_arrow_white_24dp)
            }
        }
    }

    fun checkTrackName(name: String) {
        trackName.apply {
            isDisplayed()
            hasText(name)
        }
    }

    fun checkSlidingBtn() {
        slidingBtn.apply {
            isDisplayed()
            withDrawable(R.drawable.ic_expand_less_white_24dp)
        }
    }

    fun getProgress() = getProgressForProgressBar(onView(trackProgress))
}