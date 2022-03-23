package test

import common.BaseTest
import org.junit.Test
import page.*
import utils.step

private const val TIMEOUT_PLAYER = 5000L
private const val SLIPPAGE_PROGRESS_BAR = 500L

class PlayPauseTest : BaseTest() {

    @Test
    fun playStopMiniPlayerTest() {
        var trackName = ""
        var progress: Int
        PageWelcome.clickGetStartedBtn()
        step("Step_1. Проверить что после выбора трека начинается воспроизведение") {
            trackName = PageContentContainer.clickRandomTrack()
            PageMiniPlayer {
                // Проверить что после выбора трека начинается воспроизведение
                checkSlidingBtn()
                checkTrackName(trackName)
                checkPlayerPauseBtn(true)
                progress = getProgress()
                pause(TIMEOUT_PLAYER)
                if (progress >= getProgress()) {
                    throw AssertionError(
                        "Progress play error: last progress = $progress, " +
                                "current progress = ${getProgress()}"
                    )
                }
            }
        }
        step("Step_2. Проверить что воспросизведение останавливается по нажатию на паузу") {
            PageMiniPlayer {
                clickPlayerPauseBtn()
                checkSlidingBtn()
                checkTrackName(trackName)
                checkPlayerPauseBtn(false)
                progress = getProgress()
                pause(TIMEOUT_PLAYER)
                if (progress + SLIPPAGE_PROGRESS_BAR < getProgress()) {
                    throw AssertionError(
                        "Progress pause error: last progress = $progress, " +
                                "current progress = ${getProgress()}"
                    )
                }
            }
        }
        step("Step_3. Проверить что воспросизведение возобновляется по нажатию на плей") {
            PageMiniPlayer {
                clickPlayerPauseBtn()
                checkSlidingBtn()
                checkTrackName(trackName)
                checkPlayerPauseBtn(true)
                progress = getProgress()
                pause(TIMEOUT_PLAYER)
                if (progress >= getProgress()) {
                    throw AssertionError(
                        "Progress play error: last progress = $progress, " +
                                "current progress = ${getProgress()}"
                    )
                }
            }
        }
    }

    @Test
    fun playStopPlayerTest() {
        var trackName = ""
        var progress: Int
        var progressTime: Int
        PageWelcome.clickGetStartedBtn()
        step("Step_1. Проверить что трек воспроизводится в плеере") {
            trackName = PageContentContainer.clickRandomTrack()

            PageMiniPlayer.clickOnMiniPlayer()
            PagePlayerController.checkPlayerPauseBtn(true)
            PagePlayerPanel.checkCurrentTrackName(trackName)
            PagePlayerController {
                progress = getProgress()
                progressTime = getProgressSeconds()
                pause(TIMEOUT_PLAYER)
                if (progress >= getProgress()) {
                    throw AssertionError(
                        "Progress play error: last progress = $progress, " +
                                "current progress = ${PageMiniPlayer.getProgress()}"
                    )
                }
                if (progressTime >= getProgressSeconds()) {
                    throw AssertionError(
                        "Progress time play error: " +
                                "last progress time = $progressTime, " +
                                "current progress time = ${getProgressSeconds()}"
                    )
                }
            }
        }
        step("Step_2. Проверить что воспросизведение останавливается по нажатию на паузу") {
            PagePlayerController.clickPlayerPauseBtn()
            PagePlayerController.checkPlayerPauseBtn(false)
            PagePlayerPanel.checkCurrentTrackName(trackName)
            PagePlayerController {
                progress = getProgress()
                progressTime = getProgressSeconds()
                pause(TIMEOUT_PLAYER)
                if (progress + SLIPPAGE_PROGRESS_BAR < getProgress()) {
                    throw AssertionError("Progress pause error: " +
                            "last progress = $progress, " +
                            "current progress = ${PageMiniPlayer.getProgress()}")
                }
                if (progressTime != getProgressSeconds()) {
                    throw AssertionError("Progress time play error: " +
                            "last progress time = $progressTime, " +
                            "current progress time = ${getProgressSeconds()}")
                }
            }
        }
        step("Step_3. Проверить что воспросизведение возобновляется по нажатию на плей") {
            PagePlayerController.clickPlayerPauseBtn()
            PagePlayerController.checkPlayerPauseBtn(false)
            PagePlayerPanel.checkCurrentTrackName(trackName)
            PagePlayerController {
                progress = getProgress()
                progressTime = getProgressSeconds()
                pause(TIMEOUT_PLAYER)
                if (progress >= getProgress() || progressTime >= getProgressSeconds()) {
                    throw AssertionError("Progress play error: " +
                            "last progress = $progress, " +
                            "current progress = ${PageMiniPlayer.getProgress()}")
                }
                if (progressTime >= getProgressSeconds()) {
                    throw AssertionError("Progress time play error: " +
                            "last progress time = $progressTime, " +
                            "current progress time = ${getProgressSeconds()}")
                }
            }
        }
    }

    @Test
    fun playStopPlayerDuringTransitionTest() {
        var trackName = ""
        var progress: Int
        var progressTime: Int
        PageWelcome.clickGetStartedBtn()
        step("Step_1. Остановить воспроизведение и перейти в плеер") {
            trackName = PageContentContainer.clickRandomTrack()
            PageMiniPlayer {
                clickPlayerPauseBtn()
                clickOnMiniPlayer()
            }
            // Проверить что в плеере трек не воспроизводится
            PagePlayerController.checkPlayerPauseBtn(false)
            PagePlayerPanel.checkCurrentTrackName(trackName)
            PagePlayerController {
                progress = getProgress()
                progressTime = getProgressSeconds()
                pause(TIMEOUT_PLAYER)
                if (progress != PageMiniPlayer.getProgress()) {
                    throw AssertionError("Progress pause error: " +
                            "last progress = $progress, " +
                            "current progress = ${PageMiniPlayer.getProgress()}")
                }
                if (progressTime != getProgressSeconds()) {
                    throw AssertionError("Progress time play error: " +
                            "last progress time = $progressTime, " +
                            "current progress time = ${getProgressSeconds()}")
                }
            }
        }
        step("Step_2. Запустить воспроизведение и выйти из плеере") {
            PagePlayerController.clickPlayerPauseBtn()
            PagePlayerToolBar.clickClosePlayerBtn()
            PageMiniPlayer {
                // Проверить что в миниплеере идет воспроизведение
                checkSlidingBtn()
                checkTrackName(trackName)
                checkPlayerPauseBtn(true)
                progress = getProgress()
                pause(TIMEOUT_PLAYER)
                if (progress >= getProgress()) {
                    throw AssertionError("Progress play error: last progress = $progress, " +
                            "current progress = ${getProgress()}")
                }
            }
        }
        step("Step_3. Вернуться в плеер, остановить воспроизведение и выйти из плеера") {
            PageMiniPlayer.clickOnMiniPlayer()
            PagePlayerController.clickPlayerPauseBtn()
            PagePlayerToolBar.clickClosePlayerBtn()
            PageMiniPlayer {
                // Проверить что в миниплеере трек не воспроизводится
                checkSlidingBtn()
                checkTrackName(trackName)
                checkPlayerPauseBtn(false)
                progress = getProgress()
                pause(TIMEOUT_PLAYER)
                if (progress < getProgress()) {
                    throw AssertionError("Progress pause error: last progress = $progress, " +
                            "current progress = ${getProgress()}")
                }
            }
        }
    }

    @Test
    fun playStopPlayerDuringTransitionLandscapeTest() {
        var trackName = ""
        var progress: Int
        var progressTime: Int
        PageWelcome.clickGetStartedBtn()
        step("Step_1. Перевести девайс в горизонтальный режим") {
            trackName = PageContentContainer.clickRandomTrack()
            setEnableLandscapeMode(true)
            // Остановить воспроизведение и перейти в плеер
            PageMiniPlayer {
                clickPlayerPauseBtn()
                clickOnMiniPlayer()
            }
            // Проверить что в плеере трек не воспроизводится
            PagePlayerController.checkPlayerPauseBtn(false)
            PagePlayerToolBar.checkCurrentSongLandscapeMode(trackName)
            PagePlayerController {
                progress = getProgress()
                progressTime = getProgressSeconds()
                pause(TIMEOUT_PLAYER)
                if (progress != PageMiniPlayer.getProgress()) {
                    throw AssertionError("Progress pause error: " +
                            "last progress = $progress, " +
                            "current progress = ${PageMiniPlayer.getProgress()}")
                }
                if (progressTime != getProgressSeconds()) {
                    throw AssertionError("Progress time play error: " +
                            "last progress time = $progressTime, " +
                            "current progress time = ${getProgressSeconds()}")
                }
            }
        }
        step("Step_2. Запустить воспроизведение и выйти из плеере") {
            PagePlayerController.clickPlayerPauseBtn()
            // Перевести девайс в вертикальный режим
            setEnableLandscapeMode(false)
            PagePlayerToolBar.clickClosePlayerBtn()
            PageMiniPlayer {
                // Проверить что в миниплеере идет воспроизведение
                checkSlidingBtn()
                checkTrackName(trackName)
                checkPlayerPauseBtn(true)
                progress = getProgress()
                pause(TIMEOUT_PLAYER)
                if (progress >= getProgress()) {
                    throw AssertionError("Progress play error: last progress = $progress, " +
                            "current progress = ${getProgress()}")
                }
            }
        }
        step("Step_3. Вернуться в плеер, остановить воспроизведение и выйти из плеера") {
            PageMiniPlayer.clickOnMiniPlayer()
            PagePlayerController.clickPlayerPauseBtn()
            // Перевести девайс в горизонтальный режим
            setEnableLandscapeMode(true)
            PagePlayerToolBar.clickClosePlayerBtn()
            PageMiniPlayer {
                // Проверить что в миниплеере трек не воспроизводится
                checkSlidingBtn()
                checkTrackName(trackName)
                checkPlayerPauseBtn(false)
                progress = getProgress()
                pause(TIMEOUT_PLAYER)
                if (progress < getProgress()) {
                    throw AssertionError("Progress pause error: last progress = $progress, " +
                            "current progress = ${getProgress()}")
                }
            }
        }
    }
}