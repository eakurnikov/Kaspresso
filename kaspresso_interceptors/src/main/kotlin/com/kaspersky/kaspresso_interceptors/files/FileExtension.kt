package com.kaspersky.kaspresso_interceptors.files

enum class FileExtension {

    TXT {
        override fun toString() = ".txt"
    },
    XML {
        override fun toString() = ".xml"
    },
    PNG {
        override fun toString() = ".png"
    },
    MP4 {
        override fun toString() = ".mp4"
    }
}