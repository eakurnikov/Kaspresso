package com.kaspersky.kaspresso_interceptors.files.testresults

class TestResultsProvider(
        groupByRunNumbers: Boolean = true,
        addTimestamps: Boolean = false
) {
    val dirs = TestResultsDirsProvider(
            groupByRunNumbers = groupByRunNumbers
    )
    val files = TestResultsFilesProvider(
            testResultsDirsProvider = dirs,
            addTimestamps = addTimestamps
    )
}
