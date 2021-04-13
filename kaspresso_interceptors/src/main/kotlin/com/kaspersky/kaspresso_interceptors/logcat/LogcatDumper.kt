package com.kaspersky.kaspresso_interceptors.logcat

import com.kaspersky.kaspresso.internal.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso_interceptors.files.testresults.TestResultsFilesProvider
import java.io.File

class LogcatDumper(
    private val testResultsFilesProvider: TestResultsFilesProvider,
    private val logger: UiTestLogger
) {
    fun dumpLogcat(tag: String): File? = runCatching {
        testResultsFilesProvider.provideLogcatFile(tag).also { logger.dump(it) }
    }.onFailure { e: Throwable ->
        logger.e("Logcat dumping error occurred: ${e.getStackTraceAsString()}")
    }.getOrNull()
}