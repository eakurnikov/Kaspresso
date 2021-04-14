package com.kaspersky.kaspresso_interceptors.interceptors.step.testrun

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import com.kaspersky.kaspresso_interceptors.files.attachVideoToReport
import com.kaspersky.kaspresso_interceptors.files.video.VideoRecorder

class VideoTestInterceptor(
    private val videoRecorder: VideoRecorder
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo) {
        videoRecorder.start("Video_${testInfo.testName}")
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        videoRecorder.stop()?.attachVideoToReport()
    }
}