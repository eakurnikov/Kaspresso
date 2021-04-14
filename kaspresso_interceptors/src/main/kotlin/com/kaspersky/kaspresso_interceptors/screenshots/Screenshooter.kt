package com.kaspersky.kaspresso_interceptors.screenshots

import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.CombinedScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.InternalScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ScreenshotMaker
import com.kaspersky.kaspresso.internal.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso_interceptors.files.testresults.TestResultsFilesProvider
import java.io.File

class ScreenShooter(
    private val testResultsFilesProvider: TestResultsFilesProvider,
    private val logger: UiTestLogger,
    activities: Activities
) {
    private val screenshotMaker: ScreenshotMaker = CombinedScreenshotMaker(
        InternalScreenshotMaker(activities),
        ExternalScreenshotMaker(0)
    )

    fun take(tag: String): File? =
        runCatching {
            testResultsFilesProvider.provideScreenshotFile(tag).also {
                screenshotMaker.takeScreenshot(it)
            }
        }.onFailure { e: Throwable ->
            logger.e("Screenshot making error occurred: ${e.getStackTraceAsString()}")
        }.getOrNull()
}