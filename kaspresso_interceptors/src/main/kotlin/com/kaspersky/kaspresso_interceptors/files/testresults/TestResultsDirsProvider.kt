package com.kaspersky.kaspresso_interceptors.files.testresults

import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.TestMethod
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.findTestMethod
import com.kaspersky.kaspresso_interceptors.files.DirProvider
import java.io.File

class TestResultsDirsProvider(
    private val groupByRunNumbers: Boolean
) {
    private val dirProvider = DirProvider()
    private val testRunsCount = mutableMapOf<TestMethod, Int>()

    fun provide(path: File, subDir: String? = null): File {
        val rootDir: File = dirProvider.provide(path)
        val resultsDir: File = provideResultsDir(rootDir, subDir)
        return dirProvider.clear(resultsDir)
    }

    fun onNewTestRun() {
        val testMethod: TestMethod = Thread.currentThread().stackTrace.findTestMethod()
        testRunsCount[testMethod] = (testRunsCount[testMethod] ?: 0) + 1
    }

    private fun provideResultsDir(rootDir: File, subDir: String? = null): File {
        val testMethod: TestMethod = Thread.currentThread().stackTrace.findTestMethod()
        val runNumber: Int = testRunsCount[testMethod] ?: 1
        val resultsDirName: String = getResultsDirName(testMethod, runNumber)
        return when (subDir) {
            null -> rootDir.resolve(resultsDirName)
            else -> rootDir.resolve(subDir).resolve(resultsDirName)
        }
    }

    private fun getResultsDirName(testMethod: TestMethod, runNumber: Int): String {
        val clearedClassName: String =
            testMethod.className.replace("[^A-Za-z0-9._-]".toRegex(), "_")
        val directDirName = "$clearedClassName${File.separator}${testMethod.methodName}"
        return "${if (groupByRunNumbers) "run_$runNumber${File.separator}" else ""}$directDirName"
    }
}