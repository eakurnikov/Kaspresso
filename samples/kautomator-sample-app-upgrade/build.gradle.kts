plugins {
    id("convention.android-app")
}

android {
    defaultConfig {
        applicationId = "com.kaspersky.kaspresso.sample_upgrade_tests"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.appcompat)

    androidTestImplementation(libs.uiAutomator)
    androidTestImplementation(libs.androidXRules)
    androidTestImplementation(projects.kaspresso)
    androidTestImplementation(projects.kautomator)
}
