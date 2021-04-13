package com.kaspersky.kaspresso_interceptors.interceptors.step.testrun

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import com.kaspersky.kaspresso_interceptors.files.testresults.TestResultsDirsProvider

class DispatchingTestInterceptor(
    private val testResultsDirsProvider: TestResultsDirsProvider
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo): Unit = testResultsDirsProvider.onNewTestRun()
}