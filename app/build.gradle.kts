plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kahootquiz.android.application.compose)
    alias(libs.plugins.kahootquiz.android.application.hilt)
}

android {
    namespace = "dev.adriankuta.kahootquiz"

    defaultConfig {
        applicationId = "dev.adriankuta.kahootquiz"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.ui.quiz)
    implementation(projects.domain)

    implementation(projects.model.data)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.hilt.navigation.compose)
}