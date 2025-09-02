plugins {
    alias(libs.plugins.kahootquiz.android.library)
    alias(libs.plugins.kahootquiz.android.library.hilt)
}

android {
    namespace = "dev.adriankuta.kahootquiz.core.network"

    defaultConfig {
        buildConfigField("String", "KAHOOT_BASE_URL", "\"https://create.kahoot.it\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    // Gson for JSON serialization/deserialization
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
}
