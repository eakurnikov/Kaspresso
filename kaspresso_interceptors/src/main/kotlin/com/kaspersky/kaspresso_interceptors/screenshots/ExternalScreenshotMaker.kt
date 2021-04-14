package com.kaspersky.kaspresso_interceptors.screenshots

import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ScreenshotMaker
import com.kaspersky.kaspresso.device.uiDevice
import java.io.File

class ExternalScreenshotMaker(
    private val quality: Int = 90
) : ScreenshotMaker {

    override fun takeScreenshot(file: File) {
        uiDevice.takeScreenshot(file, 1.0f, quality)
    }
}