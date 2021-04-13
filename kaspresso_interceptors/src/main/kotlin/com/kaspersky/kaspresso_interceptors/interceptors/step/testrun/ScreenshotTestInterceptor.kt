package com.kaspersky.kaspresso_interceptors.interceptors.step.testrun

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import com.kaspersky.kaspresso_interceptors.files.attachScreenshotToReport
import com.kaspersky.kaspresso_interceptors.screenshots.ScreenShooter

class ScreenshotTestInterceptor(
    private val screenShooter: ScreenShooter
) : TestRunWatcherInterceptor {

    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        screenShooter
            .take(makeScreenshotTag("BeforeTestSection", throwable))
            ?.attachScreenshotToReport()
    }

    override fun onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        screenShooter
            .take(makeScreenshotTag("MainTestSection", throwable))
            ?.attachScreenshotToReport()
    }

    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        screenShooter
            .take(makeScreenshotTag("AfterTestSection", throwable))
            ?.attachScreenshotToReport()
    }

    private fun makeScreenshotTag(section: String, throwable: Throwable): String =
        "Screenshot_${section}_failure_${throwable.javaClass.simpleName}"
}