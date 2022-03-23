package page

import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.atiurin.ultron.extensions.hasText
import com.atiurin.ultron.extensions.isDisplayed
import com.atiurin.ultron.page.Page
import com.kabouzeid.gramophone.R
import org.hamcrest.CoreMatchers.allOf

object PagePlayerPanel : Page<PagePlayerPanel>() {
    private val currentSong = withId(R.id.current_song)
    private val currentSongName = allOf(withId(R.id.title), isDescendantOfA(currentSong))

    fun checkCurrentTrackName(name: String) {
        currentSongName.apply {
            isDisplayed()
            hasText(name)
        }
    }
}