package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import com.kaspersky.kaspresso.device.uiDevice
import java.io.File

/**
 * Captures spoon-compatible screenshots by uiautomator.
 */
class ExternalScreenshotMaker : ScreenshotMaker {

    override fun takeScreenshot(file: File) {
       uiDevice.takeScreenshot(file)
    }
}