package com.kaspersky.kaspresso_interceptors.files.testresults

import com.kaspersky.kaspresso_interceptors.files.FileExtension
import java.io.File

class TestResultsFilesProvider(
    private val testResultsDirsProvider: TestResultsDirsProvider,
    private val addTimestamps: Boolean
) {
    private val logcatRootDir = File("logcat")
    private val screenshotsRootDir = File("screenshots")
    private val videoRootDir = File("video")
    private val viewHierarchy = File("view_hierarchy")

    fun provideLogcatFile(tag: String): File =
        testResultsDirsProvider
            .provide(logcatRootDir)
            .resolve(getFileName(tag, FileExtension.TXT))

    fun provideScreenshotFile(tag: String): File =
        testResultsDirsProvider
            .provide(screenshotsRootDir)
            .resolve(getFileName(tag, FileExtension.PNG))

    fun provideVideoFile(tag: String): File =
        testResultsDirsProvider
            .provide(videoRootDir)
            .resolve(getFileName(tag, FileExtension.MP4))

    fun provideViewHierarchyFile(tag: String): File =
        testResultsDirsProvider
            .provide(viewHierarchy)
            .resolve(getFileName(tag, FileExtension.XML))

    private fun getFileName(tag: String, ext: FileExtension): String =
        "${if (addTimestamps) "${System.currentTimeMillis()}_" else ""}$tag$ext"
}