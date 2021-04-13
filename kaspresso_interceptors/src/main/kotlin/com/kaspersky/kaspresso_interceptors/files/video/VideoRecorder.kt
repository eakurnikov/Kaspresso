package com.kaspersky.kaspresso_interceptors.files.video

import android.util.Log
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso_interceptors.files.testresults.TestResultsFilesProvider
import java.io.File

private const val START_RECORDING_TIME_MS: Long = 3_000L
private const val STOP_RECORDING_TIME_MS: Long = 2_000L

class VideoRecorder(
    private val testResultsFilesProvider: TestResultsFilesProvider,
    private val logger: UiTestLogger
) {
    private var videoRecordingThread: VideoRecordingThread? = null

    /**
     * Starts video recording. It must be manually finished with [stop] afterward.
     */
    @Synchronized
    fun start(tag: String) {
        if (videoRecordingThread != null) {
            logger.i("Video recording already started")
            return
        }
        val videoFile: File = testResultsFilesProvider.provideVideoFile(tag)
        logger.i("Video recording started for test: $tag to path: ${videoFile.absolutePath}")
        videoRecordingThread = VideoRecordingThread(videoFile, logger).apply {
            priority = Thread.MAX_PRIORITY
            start()
        }
        waitForRecordingToStart()
    }

    @Synchronized
    fun stop(): File? = videoRecordingThread
        ?.run {
            logger.i("Killing video recording process")
            killRecordingProcess()
            waitForRecordingToStop()
            interrupt()
            file
        }
        ?.apply {
            videoRecordingThread = null
        }
        ?: run {
            logger.i("Video recording was not started")
            null
        }

    private fun waitForRecordingToStart() {
        try {
            logger.i("VideoRecorder is waiting for recording to start")
            Thread.sleep(START_RECORDING_TIME_MS)
        } catch (e: InterruptedException) {
            logger.e("Recording was interrupted:\n${Log.getStackTraceString(e)}")
            stop()
        }
    }

    private fun waitForRecordingToStop() {
        try {
            logger.i("VideoRecorder is waiting for recording to stop")
            Thread.sleep(STOP_RECORDING_TIME_MS)
        } catch (e: InterruptedException) {
            logger.e("Recording was interrupted:\n${Log.getStackTraceString(e)}")
        }
    }
}
