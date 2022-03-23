package page

import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.atiurin.ultron.core.espresso.recyclerview.UltronRecyclerViewItem
import com.atiurin.ultron.core.espresso.recyclerview.withRecyclerView
import com.atiurin.ultron.custom.espresso.action.getText
import com.atiurin.ultron.page.Page
import com.kabouzeid.gramophone.R
import org.hamcrest.CoreMatchers.allOf
import kotlin.random.Random

object PageContentContainer : Page<PageContentContainer>() {
    private val trackRV = withRecyclerView(allOf(withId(R.id.recycler_view), isDisplayed()))

    class TrackRecyclerItem : UltronRecyclerViewItem(){
        val title by lazy { getChild(withId(R.id.title)) }
    }

    private fun getListItemAtPosition(position: Int): TrackRecyclerItem {
        return trackRV.getItem(position)
    }

    fun getTitleTrackByPosition(position: Int) = getListItemAtPosition(position).title.getText()

    fun getCountTrack() = trackRV.getSize()

    fun clickTrackByPosition(position: Int) = getListItemAtPosition(position).click()

    fun clickRandomTrack(): String {
        val randomTrackPosition = Random.nextInt(1, getCountTrack() - 1)
        clickTrackByPosition(randomTrackPosition)
        return getTitleTrackByPosition(randomTrackPosition)
    }
}