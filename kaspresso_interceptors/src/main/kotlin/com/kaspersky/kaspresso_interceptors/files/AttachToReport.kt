package com.kaspersky.kaspresso_interceptors.files

import io.qameta.allure.android.io.*
import io.qameta.allure.espresso.AllureAndroidLifecycle
import java.io.File

fun File.attachLogcatToReport(): Unit = AllureAndroidLifecycle.addAttachment(
    name = name,
    type = TEXT_PLAIN,
    fileExtension = TXT_EXTENSION,
    file = this
)

fun File.attachViewHierarchyToReport(): Unit = AllureAndroidLifecycle.addAttachment(
    name,
    TEXT_XML,
    XML_EXTENSION,
    this
)

fun File.attachScreenshotToReport(): Unit = AllureAndroidLifecycle.addAttachment(
    name = name,
    type = IMAGE_PNG,
    fileExtension = PNG_EXTENSION,
    file = this
)

fun File.attachVideoToReport(): Unit = AllureAndroidLifecycle.addAttachment(
    name = name,
    type = "video/mp4",
    fileExtension = FileExtension.MP4.toString(),
    file = this
)