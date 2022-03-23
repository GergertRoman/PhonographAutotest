package test

import common.BaseTest
import org.junit.Test
import page.*
import utils.step

class TrackSwitchingTest : BaseTest() {

    @Test
    fun switchingTest() {
        var firstTrackName = ""
        var nextTrackName = ""
        PageWelcome.clickGetStartedBtn()
        step("Step_1. Выбрать первый трек из списка") {
            PageContentContainer {
                clickTrackByPosition(1)
                // Запомнить название выбранного трека и название следующего трека
                firstTrackName = getTitleTrackByPosition(1)
                nextTrackName = getTitleTrackByPosition(2)
            }
        }
        step("Step_2. Перейти в плеер и переключить на следующий трек") {
            PageMiniPlayer.clickOnMiniPlayer()
            PagePlayerController.clickNextBtn()
            // Проверить что название трека сменилось на название следующего трека
            PagePlayerPanel.checkCurrentTrackName(nextTrackName)
        }
        step("Step_3. Переключить на предыдущий трек") {
            PagePlayerController.clickPrevBtn()
            // Проверить что название трека сменилось на название предыдущего трека
            PagePlayerPanel.checkCurrentTrackName(firstTrackName)
        }
    }

    @Test
    fun switchingDuringTransitionTest() {
        var firstTrackName = ""
        var nextTrackName = ""
        PageWelcome.clickGetStartedBtn()
        step("Step_1. Выбрать первый трек из списка") {
            PageContentContainer {
                clickTrackByPosition(1)
                // Запомнить название выбранного трека и название следующего трека
                firstTrackName = getTitleTrackByPosition(1)
                nextTrackName = getTitleTrackByPosition(2)
            }
        }
        step("Step_2. В плеере переключиться на следующий трек") {
            PageMiniPlayer.clickOnMiniPlayer()
            PagePlayerController.clickNextBtn()
            // Проверить что название трека в миниплеере сменилось на название следующего трека
            PagePlayerToolBar.clickClosePlayerBtn()
            PageMiniPlayer.checkTrackName(nextTrackName)
        }
        step("Step_3. В плеере переключиться на предыдущий трек") {
            PageMiniPlayer.clickOnMiniPlayer()
            PagePlayerController.clickPrevBtn()
            // Проверить что название трека в миниплеере сменилось на название предыдущего трека
            PagePlayerToolBar.clickClosePlayerBtn()
            PageMiniPlayer.checkTrackName(firstTrackName)
        }
    }

    @Test
    fun switchingChangeLandscapeTest() {
        var firstTrackName = ""
        var nextTrackName = ""
        PageWelcome.clickGetStartedBtn()
        step("Step_1. Выбрать первый трек из списка") {
            PageContentContainer {
                clickTrackByPosition(1)
                // Запомнить название выбранного трека и название следующего трека
                firstTrackName = getTitleTrackByPosition(1)
                nextTrackName = getTitleTrackByPosition(2)
            }
        }
        step("Step_2. Перейти в плеер и переключить на следующий трек") {
            PageMiniPlayer.clickOnMiniPlayer()
            PagePlayerController.clickNextBtn()
            // Перевести девайс в горизонтальный режим
            setEnableLandscapeMode(true)
            // Проверить что название трека сменилось на название следующего трека
            PagePlayerToolBar.checkCurrentSongLandscapeMode(nextTrackName)
        }
        step("Step_3. В горизонтальном режиме переключиться на следующий трек и перевести девайс в вертикальный режим") {
            PagePlayerController.clickPrevBtn()
            setEnableLandscapeMode(false)
            // Проверить что название трека сменилось на название предыдущего трека
            PagePlayerPanel.checkCurrentTrackName(firstTrackName)
        }
    }
}