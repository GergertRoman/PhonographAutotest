package page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.atiurin.ultron.custom.espresso.action.getText
import com.atiurin.ultron.custom.espresso.matcher.withDrawable
import com.atiurin.ultron.extensions.click
import com.atiurin.ultron.extensions.isClickable
import com.atiurin.ultron.extensions.isDisplayed
import com.atiurin.ultron.extensions.isEnabled
import com.atiurin.ultron.page.Page
import com.kabouzeid.gramophone.R
import utils.getProgressForProgressBar
import utils.getSeconds

object PagePlayerController : Page<PagePlayerController>() {
    private val playPauseBtn = withId(R.id.player_play_pause_fab)
    private val prevBtn = withId(R.id.player_prev_button)
    private val nextBtn = withId(R.id.player_next_button)
    private val trackProgress = withId(R.id.progress_bar)
    private val progressTime = withId(R.id.player_song_current_progress)

    fun clickPlayerPauseBtn() {
        playPauseBtn.apply {
            isClickable()
            click()
        }
    }

    fun clickPrevBtn() {
        prevBtn.apply {
            isClickable()
            click()
        }
    }

    fun clickNextBtn() {
        nextBtn.apply {
            isClickable()
            click()
        }
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

    fun getProgressSeconds() = progressTime.getText().getSeconds()

    fun getProgress() = getProgressForProgressBar(onView(trackProgress))
}