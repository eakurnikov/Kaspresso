package com.kaspersky.kaspresso_interceptors.files.video

import com.kaspersky.kaspresso.device.uiDevice
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File

class VideoRecordingThread(
    val file: File,
    private val logger: UiTestLogger
) : Thread() {

    override fun run() {
        execShellCommand("screenrecord --bit-rate 100000 --bugreport ${file.absolutePath}")
    }

    fun killRecordingProcess() {
        execShellCommand("pkill -l INT screenrecord")
    }

    private fun execShellCommand(command: String) {
        runCatching {
            uiDevice.executeShellCommand(command).trim()
        }.onFailure { e: Throwable ->
            logger.e("Adb shell command:\n${command}\nexecution failure: ${e.message}")
        }
    }
}