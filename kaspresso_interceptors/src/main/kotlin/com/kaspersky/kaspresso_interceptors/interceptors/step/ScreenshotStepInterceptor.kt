package com.kaspersky.kaspresso_interceptors.interceptors.step

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import com.kaspersky.kaspresso_interceptors.files.attachScreenshotToReport

class ScreenshotStepInterceptor(
    private val screenshots: Screenshots
) : StepWatcherInterceptor {

    override fun interceptAfterWithSuccess(stepInfo: StepInfo) {
        screenshots
            .take(makeScreenshotTag(stepInfo))
            ?.attachScreenshotToReport()
    }

    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        screenshots
            .take("${makeScreenshotTag(stepInfo)}_failure_${error.javaClass.simpleName}")
            ?.attachScreenshotToReport()
    }

    private fun makeScreenshotTag(stepInfo: StepInfo): String =
        "Screenshot_${stepInfo.testClassName}_step_${stepInfo.ordinal}"
}