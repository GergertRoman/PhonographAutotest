package page

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.atiurin.ultron.extensions.click
import com.atiurin.ultron.page.Page
import com.kabouzeid.gramophone.R

object PageWelcome : Page<PageWelcome>() {
    private val getStartedBtn = withId(R.id.mi_button_cta)

    fun clickGetStartedBtn() {
        getStartedBtn.click()
    }
}