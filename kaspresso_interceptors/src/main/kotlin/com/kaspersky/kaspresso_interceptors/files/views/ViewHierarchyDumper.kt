package com.kaspersky.kaspresso_interceptors.files.views

import com.kaspersky.kaspresso.device.uiDevice
import com.kaspersky.kaspresso.internal.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso_interceptors.files.testresults.TestResultsFilesProvider
import java.io.File

class ViewHierarchyDumper(
    private val testResultsFilesProvider: TestResultsFilesProvider,
    private val logger: UiTestLogger
) {
    fun dumpViewHierarchy(tag: String): File? = runCatching {
        testResultsFilesProvider.provideViewHierarchyFile(tag).also {
            uiDevice.dumpWindowHierarchy(it)
        }
    }.onFailure { e: Throwable ->
        logger.e("View hierarchy dumping error occurred: ${e.getStackTraceAsString()}")
    }.getOrNull()
}