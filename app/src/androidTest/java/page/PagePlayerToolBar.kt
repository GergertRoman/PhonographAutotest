package page

import androidx.appcompat.widget.AppCompatImageButton
import androidx.test.espresso.matcher.ViewMatchers.*
import com.atiurin.ultron.extensions.click
import com.atiurin.ultron.extensions.isDisplayed
import com.atiurin.ultron.page.Page
import com.kabouzeid.gramophone.R
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf

object PagePlayerToolBar : Page<PagePlayerToolBar>() {
    private val closePlayerBtn = allOf(
        withClassName(
            CoreMatchers.containsString(
                AppCompatImageButton::class.simpleName
            )
        ),
        isDescendantOfA(withId(R.id.player_toolbar))
    )

    fun clickClosePlayerBtn() {
        closePlayerBtn.click()
    }

    fun checkCurrentSongLandscapeMode(currentSong: String) {
        allOf(withText(currentSong), isDescendantOfA(withId(R.id.player_toolbar))).isDisplayed()
    }
}