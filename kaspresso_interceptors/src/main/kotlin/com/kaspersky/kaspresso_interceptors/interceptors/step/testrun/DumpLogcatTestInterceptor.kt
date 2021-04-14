package com.kaspersky.kaspresso_interceptors.interceptors.step.testrun

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import com.kaspersky.kaspresso_interceptors.files.attachLogcatToReport
import com.kaspersky.kaspresso_interceptors.logcat.LogcatDumper

class DumpLogcatTestInterceptor(
    private val logcatDumper: LogcatDumper
) : TestRunWatcherInterceptor {

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        logcatDumper.dumpLogcat("TestLogcat")?.attachLogcatToReport()
    }
}