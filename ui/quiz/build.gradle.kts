plugins {
    alias(libs.plugins.kahootquiz.android.library.compose)
    alias(libs.plugins.kahootquiz.android.library.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "dev.adriankuta.kahootquiz.ui.quiz"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.domain)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.timber)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}
