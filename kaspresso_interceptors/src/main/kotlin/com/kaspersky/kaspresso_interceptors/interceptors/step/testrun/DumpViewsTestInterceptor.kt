package com.kaspersky.kaspresso_interceptors.interceptors.step.testrun

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import com.kaspersky.kaspresso_interceptors.files.attachViewHierarchyToReport
import com.kaspersky.kaspresso_interceptors.files.views.ViewHierarchyDumper

class DumpViewsTestInterceptor(
    private val viewHierarchyDumper: ViewHierarchyDumper
) : TestRunWatcherInterceptor {

    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        viewHierarchyDumper
            .dumpViewHierarchy(makeViewHierarchyTag("BeforeTestSection", throwable))
            ?.attachViewHierarchyToReport()
    }

    override fun onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        viewHierarchyDumper
            .dumpViewHierarchy(makeViewHierarchyTag("MainTestSection", throwable))
            ?.attachViewHierarchyToReport()
    }

    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        viewHierarchyDumper
            .dumpViewHierarchy(makeViewHierarchyTag("AfterTestSection", throwable))
            ?.attachViewHierarchyToReport()
    }

    private fun makeViewHierarchyTag(section: String, throwable: Throwable): String =
        "ViewHierarchy_${section}_failure_${throwable.javaClass.simpleName}"
}